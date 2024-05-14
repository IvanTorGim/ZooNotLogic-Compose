package com.ivtogi.zoonotlogic.presentation.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.composable.DefaultButton
import com.ivtogi.zoonotlogic.presentation.composable.DefaultField
import com.ivtogi.zoonotlogic.presentation.composable.EmailField
import com.ivtogi.zoonotlogic.presentation.composable.PasswordField
import com.ivtogi.zoonotlogic.presentation.composable.PhoneField

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
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
                Spacer(modifier = Modifier.height(64.dp))
                Text(
                    text = stringResource(id = R.string.create_account),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(32.dp))
                EmailField(
                    value = state.email,
                    error = state.emailError,
                    changeText = { viewModel.changeEmail(it) }
                )
                PasswordField(
                    value = state.password,
                    error = state.passwordError,
                    changeText = { viewModel.changePassword(it) }
                )
                DefaultField(
                    label = stringResource(id = R.string.name),
                    value = state.name,
                    error = state.nameError,
                    errorText = stringResource(id = R.string.userNotValid),
                    changeText = { viewModel.changeName(it) }
                )
                DefaultField(
                    label = stringResource(id = R.string.last_name),
                    value = state.lastName,
                    error = state.lastNameError,
                    errorText = stringResource(id = R.string.userNotValid),
                    changeText = { viewModel.changeLastName(it) }
                )
                PhoneField(
                    label = stringResource(id = R.string.phone),
                    value = state.phone,
                    error = state.phoneError,
                    errorText = "",
                    changeText = { viewModel.changePhone(it) }
                )
                Spacer(modifier = Modifier.height(32.dp))
                DefaultButton(
                    label = stringResource(id = R.string.signup),
                    onClick = { viewModel.signup { navigateToLogin() } }
                )
                DefaultButton(
                    label = stringResource(id = R.string.cancel),
                    onClick = { navigateToLogin() }
                )
                LaunchedEffect(state.signupError) {
                    if (state.signupError != null) {
                        snackbarHostState.showSnackbar(state.signupError!!)
                    }
                    viewModel.cleanSignupError()
                }
            }
        }
    }
}