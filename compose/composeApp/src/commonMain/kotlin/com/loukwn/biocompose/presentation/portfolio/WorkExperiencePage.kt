package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.launch

@Composable
fun WorkExperiencePage(
    baseGap: Dp,
    timeLabels: List<String>,
    calendarItems: List<CalendarItem>,
    modifier: Modifier = Modifier,
) {
    val cellGapAnimated = animateDpAsState(baseGap, tween(600))
    val stateRowX = rememberLazyListState() // State for the first Row, X
    val stateRowY = rememberLazyListState() // State for the second Row, Y
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollableState { delta ->
        scope.launch {
            stateRowX.scrollBy(-delta)
            stateRowY.scrollBy(-delta)
        }
        delta
    }

    Box(
        modifier = modifier
            .scrollable(
                state = scrollState,
                orientation = Orientation.Vertical,
                flingBehavior = ScrollableDefaults.flingBehavior(),
            )
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = stateRowX,
            userScrollEnabled = false
        ) {
            items(timeLabels.size) {
                Column(modifier = Modifier.height(cellGapAnimated.value)) {
                    if (timeLabels[it].isNotEmpty()) {
                        Divider(
                            color = Color.White.copy(alpha = .7f),
                            modifier = Modifier.height(0.5.dp)
                        )
                        Text(
                            text = timeLabels[it],
                            color = Color.White.copy(alpha = .7f),
                        )
                    }
                }
            }
        }
        LazyColumn(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(.66f)
                .align(Alignment.CenterEnd),
            state = stateRowY,
            userScrollEnabled = false,
        ) {
            items(calendarItems.size) {
                when (val item = calendarItems[it]) {
                    is CalendarItem.Gap -> {
                        Spacer(
                            modifier = Modifier
                                .height(item.size * cellGapAnimated.value)
                                .fillMaxWidth()
                        )
                    }

                    is CalendarItem.Job -> {
                        Row(
                            modifier = Modifier
                                .padding(1.dp)
                                .background(bgColor)
                                .background(
                                    item.accentColor.copy(alpha = .15f),
                                    RoundedCornerShape(16.dp)
                                )
                                .height(item.size * cellGapAnimated.value)
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(16.dp))
                                .clickable(onClick = { }),
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(12.dp)
                                    .background(item.accentColor),
                            )
                            Column(
                                modifier = Modifier
                                    .padding(
                                        horizontal = 16.dp,
                                        vertical = 8.dp,
                                    ),
                                verticalArrangement = spacedBy(2.dp),
                            ) {
                                Text(text = item.title, color = Color.White)
                                Text(
                                    text = item.durationText,
                                    color = Color.White.copy(.7f),
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }
                }
            }
            item(calendarItems.size) {
                Spacer(
                    modifier = Modifier
                        .height((Scale.MONTH_6.baseGap / baseGap) * cellGapAnimated.value)
                        .fillMaxWidth()
                )
            }
        }
    }
}