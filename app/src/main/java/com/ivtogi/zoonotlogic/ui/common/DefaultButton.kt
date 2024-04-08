package com.ivtogi.zoonotlogic.ui.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButton(label: String, loading: Boolean = false, onClick: () -> Unit) {
    if (loading) {
        CircularProgressIndicator(Modifier.height(64.dp))
    } else {
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(vertical = 8.dp),
        ) {
            Text(text = label)
        }
    }

}