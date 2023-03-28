package com.github.sgeorgiev24.leaf.presentation.view.auth.splash

import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.sgeorgiev24.leaf.presentation.common.RootScreen
import com.github.sgeorgiev24.leaf.presentation.view.auth.splash.mvi.SplashViewModel

private const val ALPHA_ANIMATION_LABEL = "alphaAnimation"
private const val SCALE_ANIMATION_LABEL = "scaleAnimation"
private const val ANIMATION_DURATION_MS = 500
private const val ANIMATION_DELAY_MS = 650

private const val INITIAL_VISIBLE_STATE = false
private const val TARGET_VISIBLE_STATE = true
private const val ALPHA_INITIAL_VALUE = 1f
private const val ALPHA_TARGET_VALUE = 0f

private const val INITIAL_SCALE_STATE = 1f
private const val TARGET_SCALE_STATE = 4f
private const val SCALE_INITIAL_VALUE = 1f
private const val SCALE_TARGET_VALUE = 4f

@Composable
fun SplashScreen() {
    val viewModel = hiltViewModel<SplashViewModel>()

    val visibleState = remember { MutableTransitionState(INITIAL_VISIBLE_STATE) }
    val alphaTransition = updateTransition(
        transitionState = visibleState,
        label = ALPHA_ANIMATION_LABEL
    )
    val animatedAlpha by alphaTransition.animateFloat(
        label = ALPHA_ANIMATION_LABEL,
        transitionSpec = { tween(ANIMATION_DURATION_MS, ANIMATION_DELAY_MS) }
    ) { state ->
        if (state) {
            ALPHA_TARGET_VALUE
        } else {
            ALPHA_INITIAL_VALUE
        }
    }

    val scaleState = remember { MutableTransitionState(INITIAL_SCALE_STATE) }
    val scaleTransition = updateTransition(
        transitionState = scaleState,
        label = SCALE_ANIMATION_LABEL
    )
    val animatedScale by scaleTransition.animateFloat(
        label = SCALE_ANIMATION_LABEL,
        transitionSpec = { tween(ANIMATION_DURATION_MS, ANIMATION_DELAY_MS) }
    ) { state ->
        if (state == INITIAL_SCALE_STATE) {
            SCALE_INITIAL_VALUE
        } else {
            SCALE_TARGET_VALUE
        }
    }

    LaunchedEffect(true) {
        visibleState.targetState = TARGET_VISIBLE_STATE
        scaleState.targetState = TARGET_SCALE_STATE
    }

    RootScreen(
        viewModel = viewModel,
        content = {
            SplashContent(
                animatedScale = animatedScale,
                animatedAlpha = animatedAlpha
            )
        }
    )
}