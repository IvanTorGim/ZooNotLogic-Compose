package com.ivtogi.zoonotlogic.presentation.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.composable.DefaultTopBar

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToHome: (String) -> Unit
) {
    val state by viewModel.state.collectAsState()

    BackHandler {
        navigateToHome(state.userId)
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = {
                DefaultTopBar(
                    userId = state.userId,
                    name = stringResource(id = R.string.profile),
                    onBackPressed = { navigateToHome(it) })
            },
            modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues)) {

                LazyRow {
                    items(state.orders) { order ->

                    }
                }
            }
        }
    }
}