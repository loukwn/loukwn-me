package com.loukwn.biocompose.presentation.aboutme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun AboutMeContent(component: AboutMeComponent, onSystemUiModeChanged: (isLight: Boolean) -> Unit) {
    LaunchedEffect(Unit) {
        onSystemUiModeChanged(false)
    }

    Box(Modifier.fillMaxSize(1f).background(Color(0xff34343f))) {
        AboutMeBg(modifier = Modifier.fillMaxSize())
    }
}
