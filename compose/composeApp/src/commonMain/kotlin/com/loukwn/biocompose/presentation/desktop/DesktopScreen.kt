package com.loukwn.biocompose.presentation.desktop

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.loukwn.biocompose.data.ScreenLogger
import com.loukwn.biocompose.presentation.designsystem.components.HoverableText
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Calendar
import compose.icons.evaicons.fill.Info
import compose.icons.evaicons.fill.Person
import compose.icons.evaicons.fill.Phone
import kotlinx.coroutines.delay
import loukwn_me_kotlin_wasm.composeapp.generated.resources.Res
import loukwn_me_kotlin_wasm.composeapp.generated.resources.phone_bg
import org.jetbrains.compose.resources.painterResource

private const val INITIAL_ANIMATION_DELAY_MS = 500L

@Composable
fun DesktopScreen(
    onSystemUiModeChanged: (isLight: Boolean) -> Unit,
    onAppPressed: (DesktopApp) -> Unit,
) {
    LaunchedEffect(Unit) {
        ScreenLogger.logScreen("desktop")
        onSystemUiModeChanged(false)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(Res.drawable.phone_bg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(100.dp),
            modifier = Modifier.fillMaxSize().padding(vertical = 32.dp),
            contentPadding = PaddingValues(24.dp),
            verticalItemSpacing = 40.dp,
            horizontalArrangement = spacedBy(16.dp),
        ) {
            items(items = DesktopApp.entries, key = { DesktopApp.entries.indexOf(it) }) {
                val index = DesktopApp.entries.indexOf(it)
                DesktopItem(
                    title = it.title,
                    imageVector = it.imageVector,
                    animationStartDelay = index * 200L + INITIAL_ANIMATION_DELAY_MS,
                    onClick = { onAppPressed(it) },
                )
            }
        }
    }
}

enum class DesktopApp(
    val title: String,
    val imageVector: ImageVector,
) {
    AboutMe("About me", EvaIcons.Fill.Person),
    Portfolio("Portfolio", EvaIcons.Fill.Calendar),
    Links("Links", EvaIcons.Fill.Phone),
    AboutThis("About This", EvaIcons.Fill.Info),
}

@Composable
private fun DesktopItem(
    imageVector: ImageVector,
    title: String,
    animationStartDelay: Long,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    var shown by remember { mutableStateOf(false) }
    val hoverInteractionSource = remember { MutableInteractionSource() }
    val isHovered by hoverInteractionSource.collectIsHoveredAsState()

    LaunchedEffect(Unit) {
        delay(animationStartDelay)
        shown = true
    }

    AnimatedVisibility(
        visible = shown,
        enter =
            slideInVertically(
                spring(Spring.DampingRatioMediumBouncy),
                initialOffsetY = { it / 2 },
            ),
    ) {
        Column(
            modifier =
                modifier
                    .fillMaxSize()
                    .hoverable(hoverInteractionSource)
                    .pointerHoverIcon(PointerIcon.Hand)
                    .clickWithBounceEffect(onClick)
                    .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = spacedBy(16.dp),
        ) {
            Box(
                modifier =
                    Modifier
                        .size(56.dp)
                        .background(Color.White, CircleShape),
            ) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = title,
                    modifier = Modifier.align(Alignment.Center).size(40.dp),
                    tint = Color(0xff4f5d73),
                )

                if (isHovered) {
                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                                .background(Color.Black.copy(alpha = .5f), CircleShape),
                    )
                }
            }

            HoverableText(
                text = title,
                textAlign = TextAlign.Center,
                hoverInteraction = hoverInteractionSource,
            )
        }
    }
}

private fun Modifier.clickWithBounceEffect(onClick: () -> Unit) =
    this then
        composed {
            var buttonIsPressed by remember { mutableStateOf(false) }
            val scale by animateFloatAsState(
                targetValue = if (buttonIsPressed) 0.80f else 1f,
                animationSpec = spring(Spring.DampingRatioMediumBouncy),
            )

            this
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                }.clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    role = Role.Button,
                    onClick = onClick,
                ).pointerInput(buttonIsPressed) {
                    awaitPointerEventScope {
                        if (buttonIsPressed) {
                            waitForUpOrCancellation()
                        } else {
                            awaitFirstDown(false)
                        }

                        buttonIsPressed = !buttonIsPressed
                    }
                }
        }
