package com.loukwn.biocompose.presentation.aboutme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.delay

private const val BottomDrawerAnimationDelayMs = 1000L

@Composable
fun FakeBottomDrawer(windowHeightDp: Dp) {
    var finishedFirstAnimation by remember { mutableStateOf(false) }

    var targetHeight by remember { mutableStateOf(0.dp) }
    val animatedHeight by animateDpAsState(
        targetHeight,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    LaunchedEffect(windowHeightDp) {
        if (finishedFirstAnimation) {
            targetHeight = windowHeightDp / 4
        }
    }

    LaunchedEffect(Unit) {
        delay(BottomDrawerAnimationDelayMs)
        targetHeight = windowHeightDp / 4
        finishedFirstAnimation = true
    }

    Box(modifier = Modifier.height(animatedHeight).fillMaxWidth().background(Color(0xff101014)))
}

@Composable
fun BottomDrawerList(
    windowHeightDp: Dp,
    scrollState: ScrollState,
) {
    var finishedFirstAnimation by remember { mutableStateOf(false) }

    var targetPaddingTop by remember { mutableStateOf(windowHeightDp) }
    val animatingPaddingTop by animateDpAsState(
        targetPaddingTop,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    LaunchedEffect(windowHeightDp) {
        if (finishedFirstAnimation) {
            targetPaddingTop = (3 * windowHeightDp) / 4
        }
    }

    LaunchedEffect(Unit) {
        delay(BottomDrawerAnimationDelayMs)
        targetPaddingTop = (3 * windowHeightDp) / 4
        finishedFirstAnimation = true
    }

    Column(
        modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
    ) {
        Spacer(modifier = Modifier.height(animatingPaddingTop))
        Column(modifier = Modifier.fillMaxWidth().background(Color.Black)) {
            BottomDrawerContent()
        }
    }
}

@Composable
private fun ColumnScope.BottomDrawerContent() {
    repeat(1000) {
        Text("haha $it", color = Color.White)
    }
}
