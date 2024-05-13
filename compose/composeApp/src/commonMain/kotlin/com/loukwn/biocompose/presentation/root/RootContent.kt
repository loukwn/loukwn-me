package com.loukwn.biocompose.presentation.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.loukwn.biocompose.presentation.desktop.DesktopContent

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    val state by remember { component.state }
    var systemUiInLightMode by remember { mutableStateOf(false) }

    Box(modifier) {
        Children(
            stack = component.stack,
            modifier = Modifier.fillMaxSize(),
            animation = stackAnimation(fade()),
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.Desktop -> DesktopContent(component = child.component)
            }
        }
        StatusBar(modifier = Modifier.fillMaxWidth(), time = state.time, inLightMode = systemUiInLightMode)
        NavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
            inLightMode = systemUiInLightMode,
            onBackClicked = {},
            onHomeClicked = {},
            onRecentsClicked = {},
        )
    }
}
