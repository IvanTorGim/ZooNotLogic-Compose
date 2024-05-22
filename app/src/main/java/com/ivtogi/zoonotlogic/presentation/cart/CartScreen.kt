package com.ivtogi.zoonotlogic.presentation.cart

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.json.responseJson
import com.github.kittinunf.result.Result
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.cart.composable.CartProductCard
import com.ivtogi.zoonotlogic.presentation.composable.DefaultBottomBar
import com.ivtogi.zoonotlogic.presentation.composable.DefaultTopBar
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import com.stripe.android.paymentsheet.rememberPaymentSheet

@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel(),
    navigateToHome: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    //TODO: FIX WHEN MODIFYING CART FROM CART SCREEN
    val paymentSheet =
        rememberPaymentSheet { onPaymentSheetResult(it, viewModel, navigateToHome, state.userId) }
    val context = LocalContext.current
    var customerConfig by remember { mutableStateOf<PaymentSheet.CustomerConfiguration?>(null) }
    var paymentIntentClientSecret by remember { mutableStateOf<String?>(null) }

    BackHandler {
        navigateToHome(state.userId)
    }

    LaunchedEffect(key1 = state.user) {
        val amount = viewModel.getStripeAmount()
        Log.i("ivan2", amount.toString())
        if (amount > 0) {
            "https://us-central1-zoo-not-logic.cloudfunctions.net/paymentSheet?amount=$amount".httpGet()
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

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = {
                DefaultTopBar(
                    userId = state.userId,
                    name = stringResource(id = R.string.cart),
                    onBackPressed = { navigateToHome(it) })
            },
            bottomBar = {
                if (state.user.cart.isNotEmpty()) {
                    val totalPrice = viewModel.getTotalAmount()
                    DefaultBottomBar(
                        label = stringResource(R.string.pay, String.format("%.2f€", totalPrice)),
                        onClick = {
                            val currentConfig = customerConfig
                            val currentClientSecret = paymentIntentClientSecret

                            if (currentConfig != null && currentClientSecret != null) {
                                presentPaymentSheet(
                                    paymentSheet,
                                    currentConfig,
                                    currentClientSecret
                                )
                            }
                        }
                    )
                }
            },
            modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->
            if (state.user.cart.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = stringResource(id = R.string.empty_cart))
                }
            }
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(state.user.cart) {
                    CartProductCard(cartProduct = it, viewModel = viewModel)
                }
            }
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
            navigateToHome(userId)
        }

        is PaymentSheetResult.Failed -> {
            print("Error: ${paymentSheetResult.error}")
        }

        is PaymentSheetResult.Completed -> {
            viewModel.cleanCart()
            navigateToHome(userId)
        }
    }
}
