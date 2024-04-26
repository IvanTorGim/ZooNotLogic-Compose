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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.domain.model.Size
import com.ivtogi.zoonotlogic.presentation.common.DefaultButton
import com.ivtogi.zoonotlogic.presentation.home.detail.comosable.MiniImageItem
import com.ivtogi.zoonotlogic.ui.theme.Primary

@Preview(showBackground = true)
@Composable
fun DetailScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
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
            Column(modifier = Modifier.padding(16.dp)) {
                Spacer(modifier = Modifier.height(24.dp))
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(5) {
                        MiniImageItem(selected = true)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Nombre del producto",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "30.00$", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(16.dp))
                Divider(modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(16.dp))
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
                        color = Primary
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Size.entries.forEach {
                        OutlinedButton(onClick = { /*TODO*/ }) { Text(text = it.name) }
                    }
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