package com.github.sgeorgiev24.leaf.presentation.view.auth.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.github.sgeorgiev24.leaf.presentation.R
import com.github.sgeorgiev24.leaf.ui.theme.ForestGreen

@Composable
fun SplashContent(
    animatedScale: Float,
    animatedAlpha: Float,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ForestGreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(.5f)
        ) {
            Icon(
                modifier = Modifier
                    .fillMaxSize()
                    .scale(animatedScale)
                    .alpha(animatedAlpha),
                painter = painterResource(id = R.drawable.ic_leaf),
                contentDescription = null,
                tint = White
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashContent(
        animatedScale = 1f,
        animatedAlpha = 1f,
    )
}