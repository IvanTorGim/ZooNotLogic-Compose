package com.ivtogi.zoonotlogic.presentation.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun ButtonWithIcon(label: String, image: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .padding(vertical = 8.dp),
    ) {
        Box(Modifier.fillMaxSize()) {
            Icon(
                imageVector = image,
                contentDescription = label,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(28.dp)
            )
            Text(text = label, modifier = Modifier.align(Alignment.Center))
        }
    }
}
