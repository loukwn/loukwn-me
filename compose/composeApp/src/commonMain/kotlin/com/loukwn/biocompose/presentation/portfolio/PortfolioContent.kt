package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.loukwn.biocompose.presentation.aboutme.AboutMeComponent
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
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
    Column(modifier = Modifier.fillMaxSize().padding(36.dp)) {
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

        Row(
            modifier = Modifier.scrollable(
                scrollState,
                Orientation.Vertical,
                flingBehavior = ScrollableDefaults.flingBehavior()
            ).fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(.5f),
                state = stateRowX,
                userScrollEnabled = false
            ) {
                items(state.timeLabels.size) {
                    Column(modifier = Modifier.height(cellGapAnimated.value)) {
                        if (state.timeLabels[it].isNotEmpty()) {
                            Divider(color = Color.White)
                            Text(
                                text = state.timeLabels[it],
                                color = Color.White,
                            )
                        }
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth(1f),
                state = stateRowY,
                userScrollEnabled = false
            ) {
                items(state.calendarItems[0].size) {
                    when (val item = state.calendarItems[0][it]) {
                        is CalendarItem.Gap -> {
                            Spacer(modifier = Modifier.height(item.size * cellGapAnimated.value).fillMaxWidth())
                        }
                        is CalendarItem.Job -> {
                            Box(
                                modifier = Modifier
                                    .background(Color.Red, RoundedCornerShape(16.dp))
                                    .height(item.size * cellGapAnimated.value)
                                    .fillMaxWidth()
                                    .clickable(onClick = { }),
                            ) {
                                Text(item.title)
                            }
                        }
                    }
                }
                item(state.calendarItems[0].size) {
                    Spacer(modifier = Modifier.height((Scale.MONTH_6.baseGap / state.baseGap) * cellGapAnimated.value).fillMaxWidth())
                }
            }
        }
    }
}