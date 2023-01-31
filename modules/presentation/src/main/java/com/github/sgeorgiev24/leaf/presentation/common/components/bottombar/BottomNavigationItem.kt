package com.github.sgeorgiev24.leaf.presentation.common.components.bottombar

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationAction

enum class BottomNavigationItem(
    val destination: NavigationAction,
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int
) {
//    Home(
//        destination = MainDests.Home,
//        labelResId = R.string.bottom_bar_home,
//        iconResId = R.drawable.ic_home
//    )
}