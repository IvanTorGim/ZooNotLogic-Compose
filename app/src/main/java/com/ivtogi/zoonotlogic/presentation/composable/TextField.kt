package com.ivtogi.zoonotlogic.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextField(
    value: String,
    changeText: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = changeText,
        label = { Text(label) },
        singleLine = true,
        maxLines = 1,
        minLines = 1,
        modifier = Modifier
            .fillMaxWidth(),
        supportingText = { Text("") }
    )
}