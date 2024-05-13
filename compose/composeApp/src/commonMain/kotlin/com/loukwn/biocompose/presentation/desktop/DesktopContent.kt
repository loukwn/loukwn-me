package com.loukwn.biocompose.presentation.desktop

import androidx.compose.animation.*
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridItemScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
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
fun DesktopContent(component: DesktopComponent) {
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
            contentPadding = PaddingValues(32.dp),
            verticalItemSpacing = 40.dp,
            horizontalArrangement = spacedBy(16.dp)
        ) {

            item(key = 0) {
                DesktopItem(
                    drawableResource = Res.drawable.avatar,
                    title = "About me",
                    animationStartDelay = 200,
                    enterTransition = scaleIn()
                )
            }

            item(key = 1) {
                DesktopItem(
                    drawableResource = Res.drawable.avatar,
                    title = "About me",
                    animationStartDelay = 600,
                    enterTransition = slideIn(initialOffset = { IntOffset(x = 100, y = 0) })
                )
            }

//            item(key = 2) {
//                DesktopItem(Res.drawable.avatar, "About me")
//            }
//
//            item(key = 3) {
//                DesktopItem(Res.drawable.avatar, "About me")
//            }
//
//            item(key = 4) {
//                DesktopItem(Res.drawable.avatar, "About me")
//            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun DesktopItem(
    drawableResource: DrawableResource,
    title: String,
    animationStartDelay: Long,
    enterTransition: EnterTransition,
    onClick: () -> Unit = {}
) {
    var shown by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(animationStartDelay)
        shown = true
    }

    AnimatedVisibility(shown, enter = enterTransition) {
        Column(
            modifier = Modifier.clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = false),
                role = Role.Button,
                onClick = onClick,
            ),
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