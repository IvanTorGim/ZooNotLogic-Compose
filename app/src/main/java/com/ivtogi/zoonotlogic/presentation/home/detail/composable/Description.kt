package com.ivtogi.zoonotlogic.presentation.home.detail.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivtogi.zoonotlogic.R

@Composable
fun Description(description: String) {
    Column {
        Text(
            text = stringResource(id = R.string.description),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = AnnotatedString(description),
            fontSize = 14.sp,
        )
    }
}