package com.ivtogi.zoonotlogic.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun PhoneField(
    label: String,
    value: String,
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        supportingText = { Text("") }
    )
}