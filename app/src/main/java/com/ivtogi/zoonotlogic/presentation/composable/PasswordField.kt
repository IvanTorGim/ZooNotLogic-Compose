package com.ivtogi.zoonotlogic.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.ivtogi.zoonotlogic.R

@Composable
fun PasswordField(
    value: String,
    error: Boolean,
    changeText: (String) -> Unit
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    OutlinedTextField(
        label = { Text(text = stringResource(id = R.string.password)) },
        value = value,
        onValueChange = { changeText(it) },
        modifier = Modifier
            .fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        visualTransformation = if (passwordHidden) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = { passwordHidden = !passwordHidden }) {
                val visibilityIcon =
                    if (passwordHidden) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description =
                    if (passwordHidden) stringResource(id = R.string.show_password)
                    else stringResource(id = R.string.hide_password)
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        },
        supportingText = { Text(text = if (error) stringResource(id = R.string.passwordNotValid) else "") },
        isError = error
    )
}