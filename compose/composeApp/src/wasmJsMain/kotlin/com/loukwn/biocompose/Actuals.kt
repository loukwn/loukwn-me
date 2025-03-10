package com.loukwn.biocompose

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.unit.IntSize

@JsFun("() => { return new Date().getFullYear() }")
actual external fun getCurrentYear(): Int

@JsFun("() => { return new Date().getMonth() + 1 }")
actual external fun getCurrentMonth(): Int

@JsFun("() => { var date = new Date(); return String(date.getHours()).padStart(2, '0') + \":\" + String(date.getMinutes()).padStart(2, '0') }")
actual external fun getFormattedTime(): String

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun getWindowSize(): IntSize {
    return LocalWindowInfo.current.containerSize
}

@JsFun("() => { return Math.floor((new Date() - new Date('1994-04-15').getTime()) / 3.15576e+10) }")
actual external fun getAgeInYears(): Int