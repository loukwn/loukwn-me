package com.loukwn.biocompose.data

import androidx.compose.ui.graphics.vector.ImageVector

data class TextLink(
    val displayText: String,
    val url: String,
)

data class IconLink(
    val icon: ImageVector,
    val url: String,
)