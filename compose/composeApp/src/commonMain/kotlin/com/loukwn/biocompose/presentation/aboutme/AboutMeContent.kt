package com.loukwn.biocompose.presentation.aboutme

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.loukwn.biocompose.presentation.root.GlobalInsetsToConsume
import kotlinx.coroutines.delay
import loukwn_me_kotlin_wasm.composeapp.generated.resources.Res
import loukwn_me_kotlin_wasm.composeapp.generated.resources.back_toolbar
import loukwn_me_kotlin_wasm.composeapp.generated.resources.me_grayscaled
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@Composable
fun AboutMeContent(component: AboutMeComponent, onSystemUiModeChanged: (isLight: Boolean) -> Unit, onBackPressed: () -> Unit) {
    LaunchedEffect(Unit) {
        onSystemUiModeChanged(false)
    }

    Box(Modifier.fillMaxSize(1f).background(Color(0xff34343f))) {
        AboutMeBg(modifier = Modifier.fillMaxSize())
        Avatar()
        TopBar(onBackPressed)
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

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun TopBar(onBackPressed: () -> Unit) {
    Column(modifier = Modifier.padding(top = GlobalInsetsToConsume.calculateTopPadding()).fillMaxWidth()) {
        Spacer(Modifier.height(32.dp))
        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp), color = Color.White.copy(alpha = .5f))
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBackPressed) {
                Image(
                    painterResource(Res.drawable.back_toolbar),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "",
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = "About me", style = MaterialTheme.typography.h1, color = Color.White)
        }
        Divider(modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp), color = Color.White.copy(alpha = .5f))
    }
}
