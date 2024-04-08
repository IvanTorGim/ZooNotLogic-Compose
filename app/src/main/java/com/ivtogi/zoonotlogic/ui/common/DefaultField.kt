package com.ivtogi.zoonotlogic.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ivtogi.zoonotlogic.R

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