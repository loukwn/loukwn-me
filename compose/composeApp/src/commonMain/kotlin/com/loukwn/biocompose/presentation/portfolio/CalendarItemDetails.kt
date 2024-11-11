package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loukwn.biocompose.presentation.root.GlobalInsetsToConsume


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
            Box(modifier = modifier.fillMaxSize().background(bgColor)) {
                Row(
                    modifier = Modifier
                        .padding(
                            top = GlobalInsetsToConsume.calculateTopPadding() + 20.dp,
                            start = 48.dp,
                            end = 48.dp,
                            bottom = GlobalInsetsToConsume.calculateBottomPadding() + 36.dp,
                        )
                        .clickable(onClick = onCalendarItemDismissed)
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(
                                key = "${calendarItem.title}-bounds",
                            ),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds,
                        )
                        .background(bgColor)
                        .background(
                            calendarItem.accentColor.copy(alpha = .15f),
                            RoundedCornerShape(16.dp)
                        )
                        .fillMaxSize()
                        .clip(RoundedCornerShape(16.dp)),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(12.dp)
                            .background(calendarItem.accentColor)
                            .sharedElement(
                                state = rememberSharedContentState(
                                    key = "${calendarItem.title}-box",
                                ),
                                animatedVisibilityScope = animatedVisibilityScope,
                            ),
                    )
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = 16.dp,
                                vertical = 8.dp,
                            )
                            .sharedElement(
                                state = rememberSharedContentState(
                                    key = "${calendarItem.title}-box-internal",
                                ),
                                animatedVisibilityScope = animatedVisibilityScope,
                            ),
                        verticalArrangement = spacedBy(2.dp),
                    ) {
                        Text(
                            text = calendarItem.title,
                            color = Color.White,
                            modifier = Modifier
                                .sharedElement(
                                    state = rememberSharedContentState(
                                        key = "${calendarItem.title}-title",
                                    ),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                )
                        )
                        Text(
                            text = calendarItem.durationText,
                            color = Color.White.copy(.7f),
                            fontSize = 12.sp,
                            modifier = Modifier
                                .sharedElement(
                                    state = rememberSharedContentState(
                                        key = "${calendarItem.title}-duration",
                                    ),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                )
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .skipToLookaheadSize()
                        ) {
                            CalendarJobContent(model = calendarItem)
                        }
                    }
                }
            }
        }
    }
}


@Composable
private fun CalendarJobContent(model: CalendarItem.Job) {
    Text("Oh hai", color = Color.White)
}
