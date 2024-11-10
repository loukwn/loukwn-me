package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.loukwn.biocompose.presentation.root.GlobalInsetsToConsume
import kotlinx.coroutines.launch

private val bgColor = Color(0xff191919)

@Composable
fun PortfolioContent(
    component: PortfolioComponent,
    onSystemUiModeChanged: (isLight: Boolean) -> Unit,
    onBackPressed: () -> Unit
) {
    val state by remember { component.state }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgColor)
                .padding(
                    top = GlobalInsetsToConsume.calculateTopPadding(),
                    bottom = 0.dp,
                    start = 36.dp,
                    end = 36.dp,
                )
        ) {
            val cellGapAnimated = animateDpAsState(state.baseGap, tween(600))

            Text(
                "PORTFOLIO",
                fontSize = 20.sp,
                color = Color.White,
                modifier = Modifier.padding(vertical = 16.dp)
            )

            TimescaleSelector(
                modifier = Modifier.padding(vertical = 16.dp).align(Alignment.End),
                scale = Scale.entries.first { it.baseGap == state.baseGap },
                onScaleSelected = component::onScaleChange
            )

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
                modifier = Modifier.scrollable(
                    scrollState,
                    Orientation.Vertical,
                    flingBehavior = ScrollableDefaults.flingBehavior()
                ).fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = stateRowX,
                    userScrollEnabled = false
                ) {
                    items(state.timeLabels.size) {
                        Column(modifier = Modifier.height(cellGapAnimated.value)) {
                            if (state.timeLabels[it].isNotEmpty()) {
                                Divider(
                                    color = Color.White.copy(alpha = .7f),
                                    modifier = Modifier.height(0.5.dp)
                                )
                                Text(
                                    text = state.timeLabels[it],
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
                    items(state.calendarItems[0].size) {
                        when (val item = state.calendarItems[0][it]) {
                            is CalendarItem.Gap -> {
                                Spacer(
                                    modifier = Modifier.height(item.size * cellGapAnimated.value)
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
                                        modifier = Modifier.fillMaxHeight().width(12.dp)
                                            .background(item.accentColor)
                                    )
                                    Column(
                                        modifier = Modifier.padding(
                                            horizontal = 16.dp,
                                            vertical = 8.dp
                                        ), verticalArrangement = spacedBy(2.dp)
                                    ) {
                                        Text(item.title, color = Color.White)
                                        Text(
                                            item.durationText,
                                            color = Color.White.copy(.7f),
                                            fontSize = 12.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                    item(state.calendarItems[0].size) {
                        Spacer(
                            modifier = Modifier.height((Scale.MONTH_6.baseGap / state.baseGap) * cellGapAnimated.value)
                                .fillMaxWidth()
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(GlobalInsetsToConsume.calculateBottomPadding() + 50.dp)
                .align(Alignment.BottomCenter)
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.Transparent,
                            bgColor
                        )
                    )
                )
        )
    }
}

@Composable
private fun TimescaleSelector(
    scale: Scale,
    modifier: Modifier = Modifier,
    onScaleSelected: (Scale) -> Unit,
) {
    Box(
        modifier
            .height(36.dp)
            .width(150.dp)
            .background(bgColor, shape = RoundedCornerShape(24.dp))
            .border(1.dp, Color.White.copy(.2f), RoundedCornerShape(24.dp)),
    ) {
        val targetOffset = when (scale) {
            Scale.YEAR_2 -> 0.dp
            Scale.YEAR -> 50.dp
            Scale.MONTH_6 -> 100.dp
        }

        val xOffsetAnimated by animateDpAsState(targetOffset,  tween(600))

        Box(
            modifier = Modifier
                .width(50.dp)
                .fillMaxHeight()
                .offset(x = xOffsetAnimated)
                .background(Color.Magenta, RoundedCornerShape(24.dp))
        )

        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .pointerHoverIcon(PointerIcon.Hand)
                    .weight(1f, fill = true)
                    .fillMaxWidth()
                    .clickable(
                        onClick = { onScaleSelected(Scale.YEAR_2) },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(true, color = Color.White, radius = 19.dp),
                        role = Role.Button,
                    )
                    .padding(start = 8.dp, bottom = 4.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text("2Y", fontSize = 11.sp, color = Color.White)
            }
            Box(
                modifier = Modifier
                    .pointerHoverIcon(PointerIcon.Hand)
                    .weight(1f, fill = true)
                    .fillMaxWidth()
                    .clickable(
                        onClick = { onScaleSelected(Scale.YEAR) },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(true, color = Color.White, radius = 21.dp),
                        role = Role.Button,
                    )
                    .padding(bottom = 4.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text("1Y", fontSize = 11.sp, color = Color.White)
            }
            Box(
                modifier = Modifier
                    .pointerHoverIcon(PointerIcon.Hand)
                    .weight(1f, fill = true)
                    .fillMaxWidth()
                    .clickable(
                        onClick = { onScaleSelected(Scale.MONTH_6) },
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(true, color = Color.White, radius = 19.dp),
                        role = Role.Button,
                    )
                    .padding(end = 8.dp, bottom = 4.dp),
                contentAlignment = Alignment.Center,
            ) {
                Text("6M", fontSize = 11.sp, color = Color.White)
            }
        }
    }
}