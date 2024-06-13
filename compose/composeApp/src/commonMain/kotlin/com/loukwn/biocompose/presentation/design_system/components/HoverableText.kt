package com.loukwn.biocompose.presentation.design_system.components

import androidx.compose.foundation.interaction.HoverInteraction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun HoverableText(
    text: String,
    textAlign: TextAlign,
    hoverInteraction: MutableInteractionSource,
    modifier: Modifier = Modifier,
) {
    val isHovered by hoverInteraction.collectIsHoveredAsState()
    val textColor = if (isHovered) Color.Gray else Color.White

    Text(text, color = textColor, modifier = modifier, textAlign = textAlign)
}