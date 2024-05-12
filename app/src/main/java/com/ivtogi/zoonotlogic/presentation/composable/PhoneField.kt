package com.ivtogi.zoonotlogic.presentation.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ivtogi.zoonotlogic.R

@Composable
fun PhoneField(
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.accent),
            focusedLabelColor = colorResource(id = R.color.accent),
            focusedLeadingIconColor = colorResource(id = R.color.accent),
            focusedPlaceholderColor = colorResource(id = R.color.accent),
            focusedTrailingIconColor = colorResource(id = R.color.accent),
            focusedTextColor = Color.Black,
            cursorColor = Color.Black
        ),
        supportingText = { Text(text = if (error) errorText else "") },
        isError = error
    )
}