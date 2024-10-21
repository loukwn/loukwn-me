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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.loukwn.biocompose.presentation.aboutme.AboutMeComponent
import kotlinx.coroutines.launch

@Composable
fun PortfolioContent(
    component: PortfolioComponent,
    onSystemUiModeChanged: (isLight: Boolean) -> Unit,
    onBackPressed: () -> Unit
) {
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
        var cellGap by remember { mutableStateOf(100.dp) }
        val cellGapAnimated = animateDpAsState(cellGap, tween(600))

        Row {
            Button(onClick = {cellGap = 50.dp}) {
                Text("2 Year")
            }
            Button(onClick = {cellGap = 100.dp}) {
                Text("Year")
            }
            Button(onClick = {cellGap = 600.dp}) {
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
                modifier = Modifier.fillMaxHeight().fillMaxWidth(.5f),
                state = stateRowX,
                userScrollEnabled = false
            ) {
                items(7) {
                    Column(modifier = Modifier.height(cellGapAnimated.value)) {
                        Divider(color = Color.White)
                        Text(
                            text = (2019 + it).toString(),
                            color = Color.White,
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.weight(1f),
                state = stateRowY,
                userScrollEnabled = false
            ) {
                item(0) {
                    Spacer(modifier = Modifier.height(3 * cellGapAnimated.value).fillMaxWidth())
                }
                item(1) {
                    Box(
                        modifier = Modifier
                            .background(Color.Red, RoundedCornerShape(16.dp))
                            .height(2.5 * cellGapAnimated.value)
                            .fillMaxWidth()
                            .clickable(onClick = { cellGap -= 50.dp }),
                    )
                }
                item(2) {
                    Spacer(modifier = Modifier.height(1.5 * cellGapAnimated.value).fillMaxWidth())
                }
            }

        }
    }
}