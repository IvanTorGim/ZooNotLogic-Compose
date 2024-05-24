package com.ivtogi.zoonotlogic.presentation.home.detail.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.domain.model.Size
import com.ivtogi.zoonotlogic.ui.theme.LightSecondary
import com.ivtogi.zoonotlogic.ui.theme.Primary

@Composable
fun SizeSelector(
    sizeSelected: String,
    onSizeClicked: (String) -> Unit,
    showSizeDialog: () -> Unit,
) {
    Row {
        Text(
            text = stringResource(id = R.string.select_size),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(id = R.string.size_chart),
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Primary,
            modifier = Modifier.clickable { showSizeDialog() }
        )
    }
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth()
    ) {
        Size.entries.forEach { size ->
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (sizeSelected == size.name) Primary else LightSecondary
                ),
                onClick = { onSizeClicked(size.name) }
            ) {
                Text(text = size.name.uppercase())
            }
        }
    }
}