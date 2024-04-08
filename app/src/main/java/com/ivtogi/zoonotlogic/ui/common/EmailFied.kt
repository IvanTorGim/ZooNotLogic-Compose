package com.ivtogi.zoonotlogic.ui.common

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = colorResource(id = R.color.accent),
            focusedLabelColor = colorResource(id = R.color.accent),
            focusedLeadingIconColor = colorResource(id = R.color.accent),
            focusedPlaceholderColor = colorResource(id = R.color.accent),
            focusedTrailingIconColor = colorResource(id = R.color.accent),
            focusedTextColor = Color.Black,
            cursorColor = Color.Black
        ),
        supportingText = { Text(text = if (error) stringResource(id = R.string.emailNotValid) else "") },
        isError = error
    )
}