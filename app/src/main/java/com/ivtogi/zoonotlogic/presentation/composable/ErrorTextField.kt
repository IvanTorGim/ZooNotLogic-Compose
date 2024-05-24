package com.ivtogi.zoonotlogic.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ErrorTextField(
    label: String,
    value: String,
    error: Boolean,
    errorText: String,
    changeText: (String) -> Unit
) {
    OutlinedTextField(
        label = { Text(text = label) },
        value = value,
        onValueChange = { changeText(it) },
        modifier = Modifier
            .fillMaxWidth(),
        maxLines = 1,
        singleLine = true,
        supportingText = { Text(text = if (error) errorText else "") },
        isError = error
    )
}