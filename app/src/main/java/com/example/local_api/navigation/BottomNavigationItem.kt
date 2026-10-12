package com.example.local_api.navigation

import androidx.annotation.DrawableRes

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)