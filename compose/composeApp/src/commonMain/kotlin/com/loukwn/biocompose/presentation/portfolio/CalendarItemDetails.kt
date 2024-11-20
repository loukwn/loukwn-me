package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.loukwn.biocompose.presentation.design_system.components.VectorIconButton
import com.loukwn.biocompose.presentation.root.GlobalInsetsToConsume
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Close
import compose.icons.evaicons.outline.Link
import compose.icons.evaicons.outline.MessageSquare
import compose.icons.evaicons.outline.Person
import compose.icons.evaicons.outline.Pin


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun CalendarItemDetails(
    calendarItem: CalendarItem?,
    modifier: Modifier = Modifier,
    onCalendarItemDismissed: () -> Unit,
) {
    val sharedTransitionScope = LocalSharedTransitionScope.current
        ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope = LocalNavAnimatedVisibilityScope.current
        ?: throw IllegalStateException("No AnimatedVisibility found")

    with(sharedTransitionScope) {
        if (calendarItem is CalendarItem.Job) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                    .clickable(
                        onClick = onCalendarItemDismissed,
                        interactionSource = null,
                        indication = null,
                    ),
            ) {
                Row(
                    modifier = Modifier
                        .padding(
                            top = GlobalInsetsToConsume.calculateTopPadding() + 20.dp,
                            start = 48.dp,
                            end = 48.dp,
                            bottom = GlobalInsetsToConsume.calculateBottomPadding() + 36.dp,
                        )
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(
                                key = "${calendarItem.company}-bounds",
                            ),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds,
                        )
                        .background(MaterialTheme.colors.background)
                        .background(
                            calendarItem.accentColor.copy(alpha = .15f),
                            RoundedCornerShape(16.dp)
                        )
                        .height(IntrinsicSize.Min)
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(16.dp))
                        .clickable(
                            onClick = {},
                            interactionSource = null,
                            indication = null,
                        ),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(12.dp)
                            .background(calendarItem.accentColor),
                    )
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = 32.dp,
                                vertical = 20.dp,
                            )
                            .sharedElement(
                                state = rememberSharedContentState(
                                    key = "${calendarItem.company}-box-internal",
                                ),
                                animatedVisibilityScope = animatedVisibilityScope,
                            ),
                        verticalArrangement = spacedBy(48.dp),
                    ) {
                        var alphaTarget by remember { mutableStateOf(0f) }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(verticalArrangement = spacedBy(4.dp)) {
                                Text(
                                    text = calendarItem.company,
                                    color = MaterialTheme.colors.onBackground,
                                    modifier = Modifier
                                        .sharedElement(
                                            state = rememberSharedContentState(
                                                key = "${calendarItem.company}-title",
                                            ),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                        )
                                        .skipToLookaheadSize(),
                                )
                                Text(
                                    text = calendarItem.durationText,
                                    color = MaterialTheme.colors.onBackground.copy(.7f),
                                    style = MaterialTheme.typography.caption,
                                    modifier = Modifier
                                        .sharedElement(
                                            state = rememberSharedContentState(
                                                key = "${calendarItem.company}-duration",
                                            ),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                        )
                                        .skipToLookaheadSize()
                                )
                            }
                            VectorIconButton(
                                imageVector = EvaIcons.Outline.Close,
                                onClick = onCalendarItemDismissed,
                            )
                        }

                        val alphaAnimated by animateFloatAsState(
                            targetValue = alphaTarget,
                            animationSpec = tween(600, delayMillis = 100),
                        )

                        LaunchedEffect(Unit) {
                            alphaTarget = 1f
                        }

                        Column(
                            modifier = Modifier
                                .alpha(alphaAnimated)
                                .fillMaxWidth()
                                .skipToLookaheadSize()
                                .verticalScroll(rememberScrollState()),
                            verticalArrangement = spacedBy(16.dp)
                        ) {
                            CalendarJobContent(model = calendarItem)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CalendarJobContent(
    model: CalendarItem.Job,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(60.dp),
    ) {
        Icon(
            imageVector = EvaIcons.Outline.Person,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
        )
        Text(
            text = model.title,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
        )
    }

    Separator()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(60.dp),
    ) {
        Icon(
            imageVector = EvaIcons.Outline.Pin,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
        )
        Text(
            text = model.location,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
        )
    }

    Separator()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(60.dp),
    ) {
        Icon(
            imageVector = EvaIcons.Outline.MessageSquare,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
        )
        Text(
            text = model.description,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
        )
    }

    Separator()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(52.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = EvaIcons.Outline.Link,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
        )
        val uriHandler = LocalUriHandler.current
        FlowRow {
            model.links.forEach {
                VectorIconButton(it.icon) {
                    uriHandler.openUri(it.url)
                }
            }
        }
    }
}

@Composable
private fun Separator() {
    Divider(
        color = MaterialTheme.colors.onBackground.copy(alpha = .3f),
        thickness = 1.dp,
        modifier = Modifier.padding(start = 84.dp),
    )
}