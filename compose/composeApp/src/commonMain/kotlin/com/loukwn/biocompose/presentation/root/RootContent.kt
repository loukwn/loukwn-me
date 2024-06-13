package com.loukwn.biocompose.presentation.root

import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.loukwn.biocompose.presentation.aboutme.AboutMeContent
import com.loukwn.biocompose.presentation.desktop.DesktopContent

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    val state by remember { component.state }

    Box(modifier) {
        Children(
            stack = component.stack,
            modifier = Modifier.fillMaxSize(),
            animation = stackAnimation(fade(tween(500))),
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.Desktop -> DesktopContent(component::onSystemUiModeChanged, component::onDesktopAppClicked)
                is RootComponent.Child.AboutMe -> AboutMeContent(component = child.component, component::onSystemUiModeChanged)
            }
        }
        StatusBar(modifier = Modifier.fillMaxWidth(), time = state.time, inLightMode = state.systemUiInLightMode)
        NavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
            inLightMode = state.systemUiInLightMode,
            onBackClicked = component::onBackClicked,
            onHomeClicked = component::onBackClicked,
            onRecentsClicked = {},
        )
    }
}
