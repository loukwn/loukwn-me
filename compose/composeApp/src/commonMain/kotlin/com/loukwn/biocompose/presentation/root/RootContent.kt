package com.loukwn.biocompose.presentation.root

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.loukwn.biocompose.presentation.aboutme.AboutMeContent
import com.loukwn.biocompose.presentation.aboutthis.AboutThisContent
import com.loukwn.biocompose.presentation.design_system.theme.BioTheme
import com.loukwn.biocompose.presentation.desktop.DesktopContent
import com.loukwn.biocompose.presentation.links.LinksContent
import com.loukwn.biocompose.presentation.portfolio.PortfolioContent

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    val state by remember { component.state }

    BioTheme {
        Box(modifier.background(Color.Black)) {
            Graph(component)
            StatusBar(
                modifier = Modifier.fillMaxWidth(),
                time = state.time,
                inLightMode = state.systemUiInLightMode
            )
            NavigationBar(
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
                inLightMode = state.systemUiInLightMode,
                onBackPressed = component::onBack,
                onHomePressed = component::onHome,
            )
        }
    }
}

@Composable
private fun Graph(component: RootComponent) {
    Children(
        stack = component.stack,
        modifier = Modifier.fillMaxSize(),
        animation = stackAnimation(fade(tween(500))),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Desktop -> DesktopContent(
                onSystemUiModeChanged = component::onSystemUiModeChanged,
                onAppClicked = component::onDesktopAppClicked,
            )
            is RootComponent.Child.AboutMe -> AboutMeContent(
                component = child.component,
                onSystemUiModeChanged = component::onSystemUiModeChanged,
                onBackPressed = component::onBack,
            )
            is RootComponent.Child.Portfolio -> PortfolioContent(
                component = child.component,
                onSystemUiModeChanged = component::onSystemUiModeChanged,
                onBackPressed = component::onBack,
            )
            is RootComponent.Child.Links -> LinksContent(
                component = child.component,
                onSystemUiModeChanged = component::onSystemUiModeChanged,
                onBackPressed = component::onBack,
            )
            is RootComponent.Child.AboutThis -> AboutThisContent(
                component = child.component,
                onBackPressed = component::onBack,
            )
        }
    }
}
