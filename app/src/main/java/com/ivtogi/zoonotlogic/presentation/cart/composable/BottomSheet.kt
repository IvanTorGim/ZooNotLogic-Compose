package com.ivtogi.zoonotlogic.presentation.cart.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.domain.model.User
import com.ivtogi.zoonotlogic.presentation.cart.CartViewModel
import com.ivtogi.zoonotlogic.presentation.composable.DefaultButton
import com.ivtogi.zoonotlogic.presentation.composable.NumberField
import com.ivtogi.zoonotlogic.presentation.composable.TextField
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    user: User,
    viewModel: CartViewModel,
    validAddress: Boolean,
    navigateToHome: (String) -> Unit,
) {
    val paymentSheet =
        rememberPaymentSheet { onPaymentSheetResult(it, viewModel, navigateToHome, user.id) }
    val context = LocalContext.current
    var customerConfig by remember { mutableStateOf<PaymentSheet.CustomerConfiguration?>(null) }
    var paymentIntentClientSecret by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(context) {
        val amount = viewModel.getStripeAmount()
        if (amount > 0) {
            "https://us-central1-zoo-not-logic.cloudfunctions.net/paymentSheet?amount=$amount&email=${user.email}"
                .httpGet()
                .responseJson { _, _, result ->
                    if (result is Result.Success) {
                        val responseJson = result.get().obj()
                        paymentIntentClientSecret = responseJson.getString("paymentIntent")
                        customerConfig = PaymentSheet.CustomerConfiguration(
                            responseJson.getString("customer"),
                            responseJson.getString("ephemeralKey")
                        )
                        val publishableKey = responseJson.getString("publishableKey")
                        PaymentConfiguration.init(context, publishableKey)
                    }
                }

        }
    }

    ModalBottomSheet(
        onDismissRequest = { viewModel.hideBottomSheet() },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(text = stringResource(id = R.string.shipping_address), fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            TextField(
                value = user.address,
                changeText = { viewModel.changeAddress(it) },
                label = stringResource(id = R.string.address)
            )
            TextField(
                value = user.city,
                changeText = { viewModel.changeCity(it) },
                label = stringResource(id = R.string.city)
            )
            NumberField(
                value = user.postalCode,
                onValueChange = { viewModel.changePostalCode(it) },
                label = stringResource(id = R.string.postal_code)
            )
            DefaultButton(
                label = stringResource(id = R.string.pay_now),
                onClick = {
                    val currentConfig = customerConfig
                    val currentClientSecret = paymentIntentClientSecret

                    if (currentConfig != null && currentClientSecret != null) {
                        presentPaymentSheet(paymentSheet, currentConfig, currentClientSecret)
                    }
                },
                enabled = validAddress
            )
        }
    }
}


private fun presentPaymentSheet(
    paymentSheet: PaymentSheet,
    customerConfig: PaymentSheet.CustomerConfiguration,
    paymentIntentClientSecret: String
) {
    paymentSheet.presentWithPaymentIntent(
        paymentIntentClientSecret,
        PaymentSheet.Configuration(
            merchantDisplayName = "Zoo Not Logic",
            customer = customerConfig,
            // Set `allowsDelayedPaymentMethods` to true if your business handles
            // delayed notification payment methods like US bank accounts.
            allowsDelayedPaymentMethods = true
        )
    )
}

private fun onPaymentSheetResult(
    paymentSheetResult: PaymentSheetResult,
    viewModel: CartViewModel,
    navigateToHome: (String) -> Unit,
    userId: String
) {
    when (paymentSheetResult) {
        is PaymentSheetResult.Canceled -> {
            viewModel.hideBottomSheet()
        }

        is PaymentSheetResult.Failed -> {
            print("Error: ${paymentSheetResult.error}")
        }

        is PaymentSheetResult.Completed -> {
            viewModel.saveOrder()
            viewModel.cleanCart()
            navigateToHome(userId)
        }
    }
}