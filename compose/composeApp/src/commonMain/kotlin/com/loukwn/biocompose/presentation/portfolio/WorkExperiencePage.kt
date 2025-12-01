package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
internal fun WorkExperiencePage(
    containerScrollableState: ScrollableState,
    timeLabelLazyListState: LazyListState,
    calendarItemLazyListState: LazyListState,
    baseGap: Dp,
    timeLabels: List<String>,
    calendarItems: List<CalendarItem>,
    modifier: Modifier = Modifier,
    onCalendarItemClicked: (CalendarItem) -> Unit,
) {
    val sharedTransitionScope =
        LocalSharedTransitionScope.current
            ?: throw IllegalStateException("No SharedElementScope found")
    val animatedVisibilityScope =
        LocalNavAnimatedVisibilityScope.current
            ?: throw IllegalStateException("No AnimatedVisibility found")

    val cellGapAnimated = animateDpAsState(baseGap, spring(Spring.DampingRatioMediumBouncy))

    Box(
        modifier =
            modifier
                .scrollable(
                    state = containerScrollableState,
                    orientation = Orientation.Vertical,
                    flingBehavior = ScrollableDefaults.flingBehavior(),
                ),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = timeLabelLazyListState,
            contentPadding = PaddingValues(top = 20.dp, bottom = 40.dp),
            userScrollEnabled = false,
        ) {
            items(timeLabels.size) {
                Column(modifier = Modifier.height(cellGapAnimated.value)) {
                    if (timeLabels[it].isNotEmpty()) {
                        Divider(
                            color = MaterialTheme.colors.onBackground.copy(alpha = .7f),
                            modifier = Modifier.height(0.5.dp),
                        )
                        Text(
                            text = timeLabels[it],
                            color = MaterialTheme.colors.onBackground.copy(alpha = .7f),
                        )
                    }
                }
            }
        }
        LazyColumn(
            modifier =
                Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(.66f)
                    .align(Alignment.CenterEnd),
            state = calendarItemLazyListState,
            contentPadding = PaddingValues(top = 20.dp, bottom = 40.dp),
            userScrollEnabled = false,
        ) {
            items(calendarItems.size) {
                when (val item = calendarItems[it]) {
                    is CalendarItem.Gap -> {
                        Spacer(
                            modifier =
                                Modifier
                                    .height(item.size * cellGapAnimated.value)
                                    .fillMaxWidth(),
                        )
                    }

                    is CalendarItem.Job -> {
                        with(sharedTransitionScope) {
                            Row(
                                modifier =
                                    Modifier
                                        .padding(1.dp)
                                        .pointerHoverIcon(PointerIcon.Hand)
                                        .clickable(onClick = { onCalendarItemClicked(item) })
                                        .sharedBounds(
                                            sharedContentState =
                                                rememberSharedContentState(
                                                    key = "${item.company}-bounds",
                                                ),
                                            enter = slideIn(initialOffset = { IntOffset.Zero }),
                                            exit = slideOut(targetOffset = { IntOffset.Zero }),
                                            animatedVisibilityScope = animatedVisibilityScope,
                                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds,
                                        ).background(MaterialTheme.colors.background)
                                        .background(
                                            item.accentColor.copy(alpha = .15f),
                                            RoundedCornerShape(16.dp),
                                        ).height(item.size * cellGapAnimated.value)
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(16.dp)),
                            ) {
                                Box(
                                    modifier =
                                        Modifier
                                            .fillMaxHeight()
                                            .width(12.dp)
                                            .background(item.accentColor),
                                )
                                Column(
                                    modifier =
                                        Modifier
                                            .padding(
                                                horizontal = 16.dp,
                                                vertical = 8.dp,
                                            ).sharedElement(
                                                sharedContentState =
                                                    rememberSharedContentState(
                                                        key = "${item.company}-box-internal",
                                                    ),
                                                animatedVisibilityScope = animatedVisibilityScope,
                                            ),
                                    verticalArrangement = spacedBy(2.dp),
                                ) {
                                    Text(
                                        text = item.company,
                                        color = MaterialTheme.colors.onBackground,
                                        modifier =
                                            Modifier
                                                .sharedElement(
                                                    sharedContentState =
                                                        rememberSharedContentState(
                                                            key = "${item.company}-title",
                                                        ),
                                                    animatedVisibilityScope = animatedVisibilityScope,
                                                ),
                                    )
                                    Text(
                                        text = item.durationText,
                                        color = MaterialTheme.colors.onBackground.copy(.7f),
                                        style = MaterialTheme.typography.caption,
                                        modifier =
                                            Modifier
                                                .sharedElement(
                                                    sharedContentState =
                                                        rememberSharedContentState(
                                                            key = "${item.company}-duration",
                                                        ),
                                                    animatedVisibilityScope = animatedVisibilityScope,
                                                ),
                                    )
                                }
                            }
                        }
                    }
                }
            }
            item(calendarItems.size) {
                Spacer(
                    modifier =
                        Modifier
                            .height((Scale.MONTH_6.baseGap / baseGap) * cellGapAnimated.value)
                            .fillMaxWidth(),
                )
            }
        }
    }
}
