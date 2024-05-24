package com.ivtogi.zoonotlogic.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.ivtogi.zoonotlogic.R

@Composable
fun EmailField(
    value: String,
    error: Boolean,
    changeText: (String) -> Unit
) {
    OutlinedTextField(
        label = { Text(text = stringResource(id = R.string.email)) },
        value = value,
        onValueChange = { changeText(it) },
        modifier = Modifier
            .fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        supportingText = { Text(text = if (error) stringResource(id = R.string.emailNotValid) else "") },
        isError = error
    )
}