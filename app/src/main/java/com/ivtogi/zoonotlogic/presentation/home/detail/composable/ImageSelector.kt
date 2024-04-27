package com.ivtogi.zoonotlogic.presentation.home.detail.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.ui.theme.Primary

@Composable
fun ImageSelector(images: List<String>, imageSelected: Int, onImageClicked: (Int) -> Unit) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        itemsIndexed(images) { index, image ->
            val selected = index == imageSelected
            AsyncImage(
                model = image,
                contentDescription = stringResource(id = R.string.product_image),
                placeholder = painterResource(id = R.drawable.butterfly),
                modifier = Modifier
                    .size(68.dp)
                    .border(
                        width = if (selected) 3.dp else 1.dp,
                        color = if (selected) Primary else Color.Gray,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { onImageClicked(index) }
            )
        }
    }
}