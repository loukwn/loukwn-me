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
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import kotlinx.coroutines.launch

@Composable
fun PortfolioContent(
    component: PortfolioComponent,
    onSystemUiModeChanged: (isLight: Boolean) -> Unit,
    onBackPressed: () -> Unit
) {

    val state by remember { component.state }
//    Row(modifier = Modifier.fillMaxSize()) {
//        val lazyListState = rememberLazyListState()
//        LazyColumn(modifier = Modifier.fillMaxHeight().fillMaxWidth(.2f), state = lazyListState) {
//            items(100) {
//                Text(text = it.toString(), color = Color.White)
//            }
//        }
//        LazyColumn(modifier = Modifier.fillMaxHeight().fillMaxWidth(.8f), state = lazyListState) {
//            items(100) {
//                Text(text = it.toString(), color = Color.White)
//            }
//        }
//    }
    Column(modifier = Modifier.fillMaxSize().background(Color(0xff1a1832)).padding(36.dp)) {
        val cellGapAnimated = animateDpAsState(state.baseGap, tween(600))

        Row {
            Button(onClick = { component.onScaleChange(Scale.YEAR_2) }) {
                Text("2 Year")
            }
            Button(onClick = { component.onScaleChange(Scale.YEAR) }) {
                Text("Year")
            }
            Button(onClick = { component.onScaleChange(Scale.MONTH_6) }) {
                Text("6 month")
            }
        }

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

//        val currentDateLineOffsetAnimated = animateDpAsState(state.currentDateLineOffset, tween(600))

        Box(
            modifier = Modifier.scrollable(
                scrollState,
                Orientation.Vertical,
                flingBehavior = ScrollableDefaults.flingBehavior()
            ).fillMaxSize()
        ) {
//            Divider(
//                color = Color.Red.copy(alpha = .4f),
//                modifier = Modifier.height(1.dp).offset(y = currentDateLineOffsetAnimated.value)
//            )
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
                modifier = Modifier.fillMaxHeight().fillMaxWidth(.66f).align(Alignment.CenterEnd),
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
                                    .background(Color(0xff151224), RoundedCornerShape(16.dp))
                                    .height(item.size * cellGapAnimated.value)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(16.dp))
                                    .clickable(onClick = { }),
                            ) {
                                Box(
                                    modifier = Modifier.fillMaxHeight().width(12.dp)
                                        .background(Color(0xff9c1c49))
                                )
                                Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), verticalArrangement = spacedBy(2.dp)) {
                                    Text(item.title, color = Color.White)
                                    Text(item.durationText, color = Color.White.copy(.7f), fontSize = 12.sp)
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
}