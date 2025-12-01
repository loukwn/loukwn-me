package com.loukwn.biocompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntSize

expect fun getCurrentYear(): Int

expect fun getCurrentMonth(): Int

expect fun getFormattedTime(): String

@Composable
expect fun getWindowSize(): IntSize

expect fun getAgeInYears(): Int
