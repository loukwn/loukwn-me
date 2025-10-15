package com.loukwn.biocompose.presentation.util

import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize

fun IntSize.toPx(density: Density): DpSize =
    with(density) {
        DpSize(width.toDp(), height.toDp())
    }
