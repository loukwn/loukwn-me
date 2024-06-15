package com.loukwn.biocompose.presentation.aboutme

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import loukwn_me_kotlin_wasm.composeapp.generated.resources.Res
import loukwn_me_kotlin_wasm.composeapp.generated.resources.me_grayscaled
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@Composable
fun AboutMeContent(component: AboutMeComponent, onSystemUiModeChanged: (isLight: Boolean) -> Unit) {
    LaunchedEffect(Unit) {
        onSystemUiModeChanged(false)
    }

    Box(Modifier.fillMaxSize(1f).background(Color(0xff34343f))) {
        AboutMeBg(modifier = Modifier.fillMaxSize())
        Avatar()
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Avatar() {
    var targetBlurRadius by remember { mutableStateOf(25.dp) }
    val blurRadiusAnimated by animateDpAsState(targetBlurRadius, tween(1000))

    LaunchedEffect(Unit) {
        delay(200)
        targetBlurRadius = 0.dp
    }

    Image(
        painterResource(Res.drawable.me_grayscaled),
        "",
        modifier = Modifier.fillMaxSize().blur(radiusX = blurRadiusAnimated, radiusY = blurRadiusAnimated),
        contentScale = ContentScale.Crop
    )
}
