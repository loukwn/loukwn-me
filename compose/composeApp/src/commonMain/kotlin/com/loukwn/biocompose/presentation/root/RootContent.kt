package com.loukwn.biocompose.presentation.root

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.arkivanov.decompose.extensions.compose.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.stack.animation.stackAnimation
import com.loukwn.biocompose.presentation.desktop.DesktopContent

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    val state by remember { component.state }

    Column(modifier, verticalArrangement = Arrangement.Bottom) {
        Text(state.time)
        Children(
            stack = component.stack,
            modifier = Modifier.weight(1f).fillMaxWidth(),
            animation = stackAnimation(fade()),
        ) {
            when (val child = it.instance) {
                is RootComponent.Child.Desktop -> DesktopContent(component = child.component)
            }
        }
        Text("Bottom Bar")
    }
}
