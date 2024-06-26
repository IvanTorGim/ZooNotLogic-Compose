package com.ivtogi.zoonotlogic.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.composable.DefaultButton
import com.ivtogi.zoonotlogic.presentation.composable.EmailField
import com.ivtogi.zoonotlogic.presentation.composable.PasswordField

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToSignup: () -> Unit,
    navigateToHome: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    if (!state.userId.isNullOrBlank()) {
        navigateToHome(state.userId!!)
    } else {
        Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.zoonotlogic),
                    contentDescription = stringResource(id = R.string.zoonotlogic),
                )
                Spacer(modifier = Modifier.weight(1f))
                EmailField(
                    value = state.email,
                    error = state.emailError,
                    changeText = { viewModel.changeEmail(it) })
                PasswordField(
                    value = state.password,
                    error = state.passwordError,
                    changeText = { viewModel.changePassword(it) }
                )
                Spacer(modifier = Modifier.height(24.dp))
                DefaultButton(
                    label = stringResource(id = R.string.login),
                    onClick = { viewModel.login { navigateToHome(it) } }
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Divider(Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.divider_text),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Divider(Modifier.weight(1f))
                }
                Row {
                    Text(text = stringResource(id = R.string.not_have_account))
                    Text(
                        text = stringResource(id = R.string.sing_up),
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .clickable { navigateToSignup() },
                        color = colorResource(id = R.color.accent)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                LaunchedEffect(key1 = state.loginError) {
                    if (state.loginError != null) {
                        snackbarHostState.showSnackbar(state.loginError!!)
                        viewModel.cleanLoginError()
                    }
                }
            }
        }
    }
}

