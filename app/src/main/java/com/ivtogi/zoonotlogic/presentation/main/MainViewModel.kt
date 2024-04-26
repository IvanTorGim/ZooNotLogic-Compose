package com.ivtogi.zoonotlogic.presentation.main

import androidx.lifecycle.ViewModel
import com.ivtogi.zoonotlogic.navigation.NavigationItems
import com.ivtogi.zoonotlogic.navigation.NavigationItems.Admin
import com.ivtogi.zoonotlogic.navigation.NavigationItems.Cart
import com.ivtogi.zoonotlogic.navigation.NavigationItems.Home
import com.ivtogi.zoonotlogic.navigation.NavigationItems.Profile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun getNavBarItems(): List<NavigationItems> {
        return if (true) listOf(Admin, Home, Cart, Profile) else listOf(Home, Cart, Profile)
    }

}