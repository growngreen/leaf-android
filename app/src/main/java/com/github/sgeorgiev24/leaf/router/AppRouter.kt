@file:OptIn(ExperimentalMaterialApi::class)

package com.github.sgeorgiev24.leaf.router

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.plusAssign
import com.github.sgeorgiev24.leaf.presentation.common.components.bottombar.BottomNavigationItem
import com.github.sgeorgiev24.leaf.presentation.common.components.bottombar.LeafBottomBar
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationCommand
import com.github.sgeorgiev24.leaf.presentation.navigation.NavigationDispatcher
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.AuthDests
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.CategoryDests
import com.github.sgeorgiev24.leaf.presentation.navigation.destinations.MainDests
import com.github.sgeorgiev24.leaf.presentation.navigation.wrapper.bottomSheetHolder
import com.github.sgeorgiev24.leaf.presentation.navigation.wrapper.composableHolder
import com.github.sgeorgiev24.leaf.presentation.view.auth.signin.SignInScreen
import com.github.sgeorgiev24.leaf.presentation.view.auth.signup.SignUpScreen
import com.github.sgeorgiev24.leaf.presentation.view.auth.splash.SplashScreen
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.EditCategoriesScreen
import com.github.sgeorgiev24.leaf.presentation.view.main.categories.edit.EditCategoryScreen
import com.github.sgeorgiev24.leaf.presentation.view.main.home.HomeScreen
import com.github.sgeorgiev24.leaf.ui.theme.Colors
import com.github.sgeorgiev24.leaf.ui.theme.ForestGreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.github.sgeorgiev24.leaf.ui.theme.LeafTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun AppRouter(
    navigationDispatcher: NavigationDispatcher
) {
    val navController = rememberAnimatedNavController()
    val bottomSheetNavigator = rememberFullScreenBottomSheetNavigator()
    navController.navigatorProvider += bottomSheetNavigator

    val scaffoldState = rememberScaffoldState()

    val showBottomBar = navController
        .currentBackStackEntryAsState().value?.destination?.route in
        BottomNavigationItem.values().map { it.destination.route }

    SetSystemBarsBackground(navController = navController)

    LaunchedEffect(key1 = Unit) {
        navigationDispatcher.navigationCommands.collectLatest { navigationCommand ->
            when (navigationCommand) {
                NavigationCommand.Back -> navController.popBackStack()
                is NavigationCommand.Navigate -> {
                    navController.navigate(
                        route = navigationCommand.navAction.route,
                        navOptions = navigationCommand.navAction.navOptions
                    )
                }
                is NavigationCommand.PopToDestination -> {
                    navController.popBackStack(navigationCommand.route, navigationCommand.inclusive)
                }
            }
        }
    }

    LeafTheme {
        ModalBottomSheetLayout(
            bottomSheetNavigator = bottomSheetNavigator,
            sheetShape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Scaffold(
                scaffoldState = scaffoldState,
                modifier = Modifier
                    .statusBarsPadding(),
                bottomBar = {
                    AnimatedVisibility(
                        visible = showBottomBar,
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        navController.currentBackStackEntry?.let {
                            LeafBottomBar(navBackStackEntry = it)
                        }
                    }
                },
                content = { padding ->
                    AnimatedNavHost(
                        modifier = Modifier.padding(padding),
                        navController = navController,
                        startDestination = AuthDests.Splash.route // TODO change this
                    ) {
                        authDestinations()
                        mainDestinations()
                        categoryDestinations()
                    }
                },
                drawerShape = RectangleShape,
            )
        }
    }
}

@Composable
private fun SetSystemBarsBackground(
    navController: NavHostController
) {
    val coroutineScope = rememberCoroutineScope()
    val systemUiController = rememberSystemUiController()
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination
    val backgroundColor = Colors.background
    LaunchedEffect(currentDestination) {
        if (currentDestination?.route == AuthDests.Splash.route) {
            systemUiController.setStatusBarColor(ForestGreen)
            systemUiController.setNavigationBarColor(ForestGreen)
        } else {
            coroutineScope.launch {
                delay(1500)
                systemUiController.setStatusBarColor(backgroundColor)
                systemUiController.setNavigationBarColor(backgroundColor)
            }
        }
    }
}

private fun NavGraphBuilder.authDestinations() {
    composableHolder(AuthDests.Splash) {
        SplashScreen()
    }
    composableHolder(AuthDests.SignUp) {
        SignUpScreen()
    }
    composableHolder(AuthDests.SignIn) {
        SignInScreen()
    }
}

private fun NavGraphBuilder.mainDestinations() {
    composableHolder(MainDests.Home) {
        HomeScreen()
    }
}

private fun NavGraphBuilder.categoryDestinations() {
    bottomSheetHolder(CategoryDests.EditCategories) {
        EditCategoriesScreen()
    }
    bottomSheetHolder(CategoryDests.EditCategory) {
        EditCategoryScreen()
    }
}

@Composable
fun rememberFullScreenBottomSheetNavigator(
    animationSpec: AnimationSpec<Float> = SwipeableDefaults.AnimationSpec,
    skipHalfExpanded: Boolean = true,
): BottomSheetNavigator {
    val sheetState = rememberModalBottomSheetState(
        ModalBottomSheetValue.Hidden,
        animationSpec
    )

    if (skipHalfExpanded) {
        LaunchedEffect(sheetState) {
            snapshotFlow { sheetState.isAnimationRunning }
                .collectLatest {
                    with(sheetState) {
                        val isOpening =
                            currentValue == ModalBottomSheetValue.Hidden && targetValue == ModalBottomSheetValue.HalfExpanded
                        val isClosing =
                            currentValue == ModalBottomSheetValue.Expanded && targetValue == ModalBottomSheetValue.HalfExpanded
                        when {
                            isOpening -> animateTo(ModalBottomSheetValue.Expanded)
                            isClosing -> animateTo(ModalBottomSheetValue.Hidden)
                        }
                    }
                }
        }
    }

    return remember(sheetState) {
        BottomSheetNavigator(sheetState = sheetState)
    }
}