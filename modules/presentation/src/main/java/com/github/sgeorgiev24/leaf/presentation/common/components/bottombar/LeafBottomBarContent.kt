package com.github.sgeorgiev24.leaf.presentation.common.components.bottombar

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import com.github.sgeorgiev24.leaf.presentation.common.components.bottombar.mvi.BottomNavigationAction
import com.github.sgeorgiev24.leaf.presentation.common.components.bottombar.mvi.BottomNavigationState
import com.github.sgeorgiev24.leaf.theme.LeafTheme

@Composable
fun LeafBottomBarContent(
    state: BottomNavigationState,
    action: (BottomNavigationAction) -> Unit,
    navBackStackEntry: NavBackStackEntry
) {
    BottomNavigation(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        elevation = 0.dp,
        backgroundColor = LeafTheme.colors.background
    ) {
        state.bottomNavItems.forEach { navItem ->
            val isSelected = navBackStackEntry.destination.route == navItem.destination.route
            BottomNavigationItem(
                icon = {
                    Icon(
                        modifier = Modifier
                            .height(height = 25.dp)
                            .padding(bottom = 4.dp),
                        painter = painterResource(navItem.iconResId),
                        contentDescription = null
                    )
                },
                label = {
                    BottomBarLabel(labelResId = navItem.labelResId, isSelected = isSelected)
                },
                selected = isSelected,
                onClick = {
                    action(BottomNavigationAction.OnBottomNavItemClick(navItem.destination))
                }
            )
        }
    }
}