package com.ivtogi.zoonotlogic.presentation.home.detail.comosable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.ui.theme.Primary

@Composable
fun MiniImageItem(selected: Boolean) {
    AsyncImage(
        model = "https://firebasestorage.googleapis.com/v0/b/zoo-not-logic.appspot.com/o/products%2Ftee_butterfly_brown_0.jpg?alt=media&token=0d1caf5c-32b4-4efd-9203-d77a481f1a26",
        contentDescription = stringResource(id = R.string.product_image),
        placeholder = painterResource(id = R.drawable.butterfly),
        modifier = Modifier
            .size(68.dp)
            .border(3.dp, Primary, shape = RoundedCornerShape(10.dp))
    )
}