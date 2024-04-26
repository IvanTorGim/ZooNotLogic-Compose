package com.ivtogi.zoonotlogic.presentation.home.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.common.DefaultButton
import com.ivtogi.zoonotlogic.presentation.home.detail.comosable.MiniImageItem

@Preview(showBackground = true)
@Composable
fun DetailScreen() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.fillMaxWidth()){
                AsyncImage(
                    model = "https://firebasestorage.googleapis.com/v0/b/zoo-not-logic.appspot.com/o/products%2Ftee_butterfly_brown_0.jpg?alt=media&token=0d1caf5c-32b4-4efd-9203-d77a481f1a26",
                    contentDescription = "",
                    placeholder = painterResource(id = R.drawable.butterfly),
                    modifier = Modifier.fillMaxWidth()
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrow_back)
                    )
                }
            }
            Spacer(modifier = Modifier.height(24.dp))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(5) {
                    MiniImageItem(selected = true)
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter)
        ) {
            DefaultButton(
                label = stringResource(id = R.string.add_cart),
                loading = false,
                onClick = {/*TODO: Añadir al carro*/ }
            )
        }
    }
}