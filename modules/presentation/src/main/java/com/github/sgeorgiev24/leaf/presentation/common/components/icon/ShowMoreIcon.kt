package com.github.sgeorgiev24.leaf.presentation.common.components.icon

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.github.sgeorgiev24.leaf.presentation.R

@Composable
fun ShowMoreIcon(
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .background(
                shape = CircleShape,
                color = Color.White
            )
            .border(1.dp, color = Color.Blue, shape = CircleShape)
            .size(25.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier
                .size(23.dp),
            painter = painterResource(id = R.drawable.ic_show_more),
            contentDescription = null,
            tint = Color.Blue
        )
    }
}