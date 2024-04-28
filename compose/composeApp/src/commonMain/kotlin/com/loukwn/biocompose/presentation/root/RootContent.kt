package com.loukwn.biocompose.presentation.root

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.loukwn.biocompose.presentation.desktop.DesktopContent

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Column(modifier, verticalArrangement = Arrangement.SpaceBetween) {
        Text("Top Bar")
        Children(
            stack = component.stack,
            animation = stackAnimation(fade()),
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.Desktop -> DesktopContent(component = child.component)
            }
        }
        Text("Bottom Bar")
    }
}