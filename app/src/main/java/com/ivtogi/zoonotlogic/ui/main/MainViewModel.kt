package com.ivtogi.zoonotlogic.ui.main

import androidx.lifecycle.ViewModel
import com.ivtogi.zoonotlogic.ui.navigation.NavigationItems
import com.ivtogi.zoonotlogic.ui.navigation.NavigationItems.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    fun getNavBarItems(): List<NavigationItems> {
        return if (true) listOf(Admin, Home, Cart, Profile) else listOf(Home, Cart, Profile)
    }

}