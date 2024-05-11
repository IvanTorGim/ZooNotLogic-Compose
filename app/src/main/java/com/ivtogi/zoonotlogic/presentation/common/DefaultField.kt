package com.ivtogi.zoonotlogic.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.dp
import com.ivtogi.zoonotlogic.ui.theme.FormStroke

@Composable
fun DefaultField(
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
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        maxLines = 1,
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = FormStroke,
            focusedLabelColor = FormStroke,
            focusedLeadingIconColor = FormStroke,
            focusedPlaceholderColor = FormStroke,
            focusedTextColor = Black,
            cursorColor = Black
        ),
        supportingText = { Text(text = if (error) errorText else "") },
        isError = error
    )
}