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
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.loukwn.biocompose.getWindowSize
import com.loukwn.biocompose.presentation.root.GlobalInsetsToConsume
import com.loukwn.biocompose.presentation.util.toPx
import kotlinx.coroutines.delay
import loukwn_me_kotlin_wasm.composeapp.generated.resources.Res
import loukwn_me_kotlin_wasm.composeapp.generated.resources.back_toolbar
import loukwn_me_kotlin_wasm.composeapp.generated.resources.me
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun AboutMeContent(
    component: AboutMeComponent,
    onSystemUiModeChanged: (isLight: Boolean) -> Unit,
    onBackPressed: () -> Unit
) {
    val windowSize = getWindowSize()
    val windowSizeDp = windowSize.toPx(LocalDensity.current)

    if (windowSizeDp.height == 0.dp) return

    LaunchedEffect(Unit) {
        onSystemUiModeChanged(false)
    }

    val scrollState = rememberScrollState()
    val scrollProgress = scrollState.value / (windowSize.height.toFloat() - 250)
    val topBarBg = if (scrollProgress >= .7f) AboutMeBottomSheetBgColor else Color.Transparent

    Box(Modifier.fillMaxSize(1f).background(Color(0xff39343f))) {
        AboutMeBg(modifier = Modifier.fillMaxSize())

        TopBar(modifier = Modifier.zIndex(1f).background(topBarBg), onBackPressed)

        Column {
            Avatar(modifier = Modifier.fillMaxWidth().weight(1f))
            FakeBottomDrawer(windowHeightDp = windowSizeDp.height)
        }

        DarkScrim(darknessProgress = scrollProgress)

        BottomDrawerList(windowHeightDp = windowSizeDp.height, scrollState = scrollState)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Avatar(modifier: Modifier = Modifier) {
    var targetBlurRadius by remember { mutableStateOf(25.dp) }
    val blurRadiusAnimated by animateDpAsState(targetBlurRadius, tween(1000))

    LaunchedEffect(Unit) {
        delay(200)
        targetBlurRadius = 0.dp
    }

    Image(
        painterResource(Res.drawable.me),
        "",
        modifier = modifier.blur(radiusX = blurRadiusAnimated, radiusY = blurRadiusAnimated),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun DarkScrim(darknessProgress: Float) {
    val tweakedProgress = if (darknessProgress > 0.5f) {
        darknessProgress + .2f
    } else {
        darknessProgress
    }
    Box(modifier = Modifier.fillMaxSize().background(AboutMeBottomSheetBgColor.copy(alpha = (tweakedProgress).coerceAtMost(1f))))
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun TopBar(modifier: Modifier = Modifier, onBackPressed: () -> Unit) {
    Column(
        modifier = modifier.padding(top = GlobalInsetsToConsume.calculateTopPadding())
            .fillMaxWidth()
    ) {
        Spacer(Modifier.height(32.dp))
        Divider(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            color = Color.White.copy(alpha = .5f)
        )
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
        Divider(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp),
            color = Color.White.copy(alpha = .5f)
        )
    }
}
