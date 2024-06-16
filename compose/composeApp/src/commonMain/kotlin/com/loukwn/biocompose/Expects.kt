package com.loukwn.biocompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.IntSize

expect fun getFormattedTime(): String

@Composable
expect fun getWindowSize(): IntSize
