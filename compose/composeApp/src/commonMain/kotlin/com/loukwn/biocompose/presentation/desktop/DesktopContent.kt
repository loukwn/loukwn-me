package com.loukwn.biocompose.presentation.desktop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import loukwn_me_kotlin_wasm.composeapp.generated.resources.Res
import loukwn_me_kotlin_wasm.composeapp.generated.resources.avatar
import loukwn_me_kotlin_wasm.composeapp.generated.resources.phone_bg
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun DesktopContent(component: DesktopComponent, onAppClicked: (DesktopApp) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(Res.drawable.phone_bg),
            "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(100.dp),
            modifier = Modifier.fillMaxSize().padding(vertical = 32.dp),
            contentPadding = PaddingValues(24.dp),
            verticalItemSpacing = 40.dp,
            horizontalArrangement = spacedBy(16.dp)
        ) {
            items(items = DesktopApp.entries, key = { DesktopApp.entries.indexOf(it) }) {
                val index = DesktopApp.entries.indexOf(it)
                DesktopItem(
                    title = it.title,
                    drawableResource = it.iconResource,
                    animationStartDelay = index * 200L,
                    onClick = { onAppClicked(it) }
                )
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
enum class DesktopApp(val title: String, val iconResource: DrawableResource) {
    AboutMe("About me", Res.drawable.avatar),
    Portfolio("Portfolio", Res.drawable.avatar),
    ContactMe("Contact Me", Res.drawable.avatar),
    AboutThis("About This", Res.drawable.avatar)
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun DesktopItem(
    drawableResource: DrawableResource,
    title: String,
    animationStartDelay: Long,
    onClick: () -> Unit = {}
) {
    var shown by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(animationStartDelay)
        shown = true
    }

    AnimatedVisibility(
        visible = shown,
        enter = slideInVertically(
            spring(Spring.DampingRatioMediumBouncy),
            initialOffsetY = { it / 2 }
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(bounded = false),
                    role = Role.Button,
                    onClick = onClick,
                )
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = spacedBy(8.dp),
        ) {
            Image(
                painter = painterResource(drawableResource),
                contentDescription = title,
                modifier = Modifier.size(48.dp),
            )
            Text(title, color = Color.White, textAlign = TextAlign.Center)
        }
    }
}