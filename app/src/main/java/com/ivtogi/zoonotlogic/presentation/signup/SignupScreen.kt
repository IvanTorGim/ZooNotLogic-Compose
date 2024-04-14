package com.ivtogi.zoonotlogic.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.common.DefaultButton
import com.ivtogi.zoonotlogic.presentation.common.DefaultField
import com.ivtogi.zoonotlogic.presentation.common.EmailField
import com.ivtogi.zoonotlogic.presentation.common.PasswordField
import com.ivtogi.zoonotlogic.presentation.common.PhoneField

@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    navigationToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
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
            loading = state.signupLoading,
            onClick = { viewModel.signup { navigationToLogin() } }
        )
        DefaultButton(
            label = stringResource(id = R.string.cancel),
            onClick = { navigationToLogin() }
        )
        if (state.signupError != null) {
            Toast.makeText(LocalContext.current, state.signupError, Toast.LENGTH_LONG).show()
            viewModel.cleanSignupError()
        }
    }
}