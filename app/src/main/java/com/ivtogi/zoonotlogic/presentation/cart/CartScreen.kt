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

    val paymentSheet = rememberPaymentSheet(::onPaymentSheetResult)
    val context = LocalContext.current
    var customerConfig by remember { mutableStateOf<PaymentSheet.CustomerConfiguration?>(null) }
    var paymentIntentClientSecret by remember { mutableStateOf<String?>(null) }

    // TODO: Añadir snackbar para avisar que el máximo de productos que se pueden agregar son 3
    BackHandler {
        navigateToHome(state.userId)
    }
    // TODO: No inicia bien porque tarda mas en cargar el user y el total amount esta a 0
    LaunchedEffect(key1 = state.stripeAmount) {
        Log.i("ivan2", state.stripeAmount.toString())
        if (state.stripeAmount > 0) {
            "https://us-central1-zoo-not-logic.cloudfunctions.net/paymentSheet?amount=${state.stripeAmount}".httpGet()
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

private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
    when (paymentSheetResult) {
        is PaymentSheetResult.Canceled -> {
            print("Canceled")
        }

        is PaymentSheetResult.Failed -> {
            print("Error: ${paymentSheetResult.error}")
        }

        is PaymentSheetResult.Completed -> {
            // Display for example, an order confirmation screen
            Log.i("ivan", "Pago correcto")
        }
    }
}
