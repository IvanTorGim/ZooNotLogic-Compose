package com.ivtogi.zoonotlogic.presentation.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ivtogi.zoonotlogic.R
import com.ivtogi.zoonotlogic.presentation.composable.ButtonWithIcon
import com.ivtogi.zoonotlogic.presentation.composable.ErrorTextField
import com.ivtogi.zoonotlogic.presentation.composable.Label
import com.ivtogi.zoonotlogic.presentation.composable.NumberField
import com.ivtogi.zoonotlogic.presentation.composable.PhoneField
import com.ivtogi.zoonotlogic.presentation.composable.TextField
import com.ivtogi.zoonotlogic.presentation.profile.composable.ProfileOrderCard
import com.ivtogi.zoonotlogic.presentation.profile.composable.ProfileTopBar

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToHome: (String) -> Unit,
    navigateToLogin: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    //TODO: ADD SAVE BUTTON
    BackHandler {
        navigateToHome(state.user.id)
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(
            topBar = {
                ProfileTopBar(
                    userId = state.user.id,
                    name = stringResource(id = R.string.profile, state.user.name),
                    onBackPressed = { navigateToHome(it) },
                    onSavePressed = { viewModel.updateProfile() }
                )
            },
            modifier = Modifier
                .fillMaxSize()
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Label(text = stringResource(id = R.string.personal_info))
                Spacer(modifier = Modifier.height(16.dp))
                ErrorTextField(
                    value = state.user.name,
                    changeText = { viewModel.changeName(it) },
                    label = stringResource(id = R.string.name),
                    errorText = stringResource(id = R.string.userNotValid),
                    error = state.nameError
                )
                TextField(
                    value = state.user.lastName,
                    changeText = { viewModel.changeLastName(it) },
                    label = stringResource(id = R.string.last_name)
                )
                PhoneField(
                    value = state.user.phone,
                    changeText = { viewModel.changePhone(it) },
                    label = stringResource(id = R.string.phone)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Label(text = stringResource(id = R.string.shipping_address))
                Spacer(modifier = Modifier.height(16.dp))
                TextField(
                    value = state.user.address,
                    changeText = { viewModel.changeAddress(it) },
                    label = stringResource(id = R.string.address)
                )
                TextField(
                    value = state.user.city,
                    changeText = { viewModel.changeCity(it) },
                    label = stringResource(id = R.string.city)
                )
                NumberField(
                    value = state.user.postalCode,
                    onValueChange = { viewModel.changePostalCode(it) },
                    label = stringResource(id = R.string.postal_code)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Label(text = stringResource(id = R.string.my_orders))
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow {
                    items(state.orders) { order ->
                        ProfileOrderCard(order = order, viewModel = viewModel)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                ButtonWithIcon(
                    label = stringResource(id = R.string.logout),
                    image = Icons.Filled.ExitToApp,
                    onClick = {
                        viewModel.logout()
                        navigateToLogin()
                    }
                )
            }
        }
    }
}