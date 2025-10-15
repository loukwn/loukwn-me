@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollBy
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.loukwn.biocompose.di.viewModel
import com.loukwn.biocompose.presentation.designsystem.components.SystemUiGradientOverlay
import com.loukwn.biocompose.presentation.designsystem.components.VectorIconButton
import com.loukwn.biocompose.presentation.phone.GlobalInsetsToConsume
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.ArrowIosBack
import compose.icons.evaicons.outline.Options
import kotlinx.coroutines.launch

internal val LocalNavAnimatedVisibilityScope = compositionLocalOf<AnimatedVisibilityScope?> { null }
internal val LocalSharedTransitionScope = compositionLocalOf<SharedTransitionScope?> { null }

@Composable
fun PortfolioScreen(onBackButtonPressed: () -> Unit) {
    val viewModel: PortfolioViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    PortfolioContent(
        state = state,
        onAction = viewModel::onAction,
        onBackButtonPressed = onBackButtonPressed,
    )
}

@Composable
private fun PortfolioContent(
    state: PortfolioUiState,
    onAction: (PortfolioAction) -> Unit,
    onBackButtonPressed: () -> Unit,
) {
    var selectedCalendarItem by remember { mutableStateOf<CalendarItem?>(null) }

    SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this,
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colors.background),
            ) {
                val timeLabelLazyListState = rememberLazyListState()
                val calendarItemLazyListState = rememberLazyListState()
                val scope = rememberCoroutineScope()
                val containerScrollableState =
                    rememberScrollableState { delta ->
                        scope.launch {
                            timeLabelLazyListState.scrollBy(-delta)
                            calendarItemLazyListState.scrollBy(-delta)
                        }
                        delta
                    }

                AnimatedContent(
                    targetState = state.showCalendarItemDetails,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(500)) togetherWith
                            fadeOut(animationSpec = tween(500))
                    },
                ) { targetState ->
                    CompositionLocalProvider(
                        LocalNavAnimatedVisibilityScope provides this,
                    ) {
                        if (targetState) {
                            if (selectedCalendarItem != null) {
                                CalendarItemDetails(
                                    calendarItem = selectedCalendarItem!!,
                                    onCalendarItemDismissed = {
                                        onAction(PortfolioAction.CalendarItemSelect(null))
                                    },
                                )
                            }
                        } else {
                            PortfolioContentInternal(
                                state = state,
                                containerScrollableState = containerScrollableState,
                                timeLabelLazyListState = timeLabelLazyListState,
                                calendarItemLazyListState = calendarItemLazyListState,
                                onBackPressed = onBackButtonPressed,
                                onFilterButtonPressed = { onAction(PortfolioAction.FilterButtonPress) },
                                onPageChanged = { onAction(PortfolioAction.PageChange(pageIndex = it)) },
                                onScaleChanged = { onAction(PortfolioAction.ScaleChange(scale = it)) },
                                onCalendarItemClicked = {
                                    onAction(PortfolioAction.CalendarItemSelect(it))
                                    selectedCalendarItem = it
                                },
                            )
                        }
                    }
                }
                SystemUiGradientOverlay(
                    endColor = MaterialTheme.colors.background,
                    modifier = Modifier.renderInSharedTransitionScopeOverlay(),
                )
            }
        }
    }
}

@Composable
private fun PortfolioContentInternal(
    state: PortfolioUiState,
    containerScrollableState: ScrollableState,
    timeLabelLazyListState: LazyListState,
    calendarItemLazyListState: LazyListState,
    onBackPressed: () -> Unit,
    onFilterButtonPressed: () -> Unit,
    onPageChanged: (Int) -> Unit,
    onScaleChanged: (Scale) -> Unit,
    onCalendarItemClicked: (CalendarItem) -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(
                    top = GlobalInsetsToConsume.calculateTopPadding(),
                    bottom = 0.dp,
                    start = 36.dp,
                    end = 36.dp,
                ),
    ) {
        TopBar(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 8.dp),
            showFilterButton = state.isFilterButtonVisible,
            onBackButtonPressed = onBackPressed,
            onFilterButtonPressed = onFilterButtonPressed,
        )

        val pagerState = rememberPagerState { 2 }
        val coroutineScope = rememberCoroutineScope()

        Tabs(
            selectedTabIndex = pagerState.currentPage,
            tabs = listOf("Work Experience", "Projects"),
        ) { pageIndex ->
            coroutineScope.launch {
                onPageChanged(pageIndex)
                pagerState.animateScrollToPage(pageIndex)
            }
        }

        AnimatedVisibility(
            state.isCalendarScaleComponentVisible,
            modifier = Modifier.align(Alignment.End),
        ) {
            TimeScaleSelector(
                modifier = Modifier.padding(bottom = 16.dp),
                scale = Scale.entries.first { it.baseGap == state.baseGap },
                onScaleSelected = onScaleChanged,
            )
        }

        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false,
            modifier = Modifier.fillMaxSize(),
        ) { page ->
            when (page) {
                0 -> {
                    WorkExperiencePage(
                        containerScrollableState = containerScrollableState,
                        timeLabelLazyListState = timeLabelLazyListState,
                        calendarItemLazyListState = calendarItemLazyListState,
                        baseGap = state.baseGap,
                        timeLabels = state.timeLabels,
                        calendarItems = state.calendarItems,
                        modifier = Modifier.fillMaxSize(),
                        onCalendarItemClicked = onCalendarItemClicked,
                    )
                }

                1 -> {
                    ProjectsPage(
                        projects = state.projects,
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

@Composable
private fun Tabs(
    selectedTabIndex: Int,
    tabs: List<String>,
    onTabSelected: (Int) -> Unit,
) {
    ScrollableTabRow(
        modifier = Modifier.padding(bottom = 16.dp),
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                color = MaterialTheme.colors.secondary,
            )
        },
    ) {
        tabs.forEachIndexed { index, tabTitle ->
            Tab(
                selected = selectedTabIndex == index,
                selectedContentColor = MaterialTheme.colors.secondary,
                unselectedContentColor = MaterialTheme.colors.onBackground,
                onClick = { onTabSelected(index) },
            ) {
                Text(
                    text = tabTitle,
                    modifier =
                        Modifier
                            .padding(
                                bottom = 8.dp,
                                top = 8.dp,
                            ),
                )
            }
        }
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    showFilterButton: Boolean,
    onBackButtonPressed: () -> Unit,
    onFilterButtonPressed: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        VectorIconButton(
            imageVector = EvaIcons.Outline.ArrowIosBack,
            onClick = onBackButtonPressed,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Portfolio",
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.weight(1f),
        )
        if (showFilterButton) {
            VectorIconButton(
                imageVector = EvaIcons.Outline.Options,
                onClick = onFilterButtonPressed,
            )
        }
    }
}

@Composable
private fun TimeScaleSelector(
    scale: Scale,
    modifier: Modifier = Modifier,
    onScaleSelected: (Scale) -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = spacedBy(8.dp),
    ) {
        Text(
            text = "Time scale:",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(bottom = 2.dp),
        )

        Box(
            Modifier
                .height(36.dp)
                .width(150.dp)
                .background(MaterialTheme.colors.background, shape = RoundedCornerShape(24.dp))
                .border(1.dp, Color.White.copy(.2f), RoundedCornerShape(24.dp)),
        ) {
            val targetOffset =
                when (scale) {
                    Scale.YEAR_2 -> 0.dp
                    Scale.YEAR -> 50.dp
                    Scale.MONTH_6 -> 100.dp
                }

            val xOffsetAnimated by animateDpAsState(targetOffset, tween(600))

            Box(
                modifier =
                    Modifier
                        .width(50.dp)
                        .fillMaxHeight()
                        .offset(x = xOffsetAnimated)
                        .background(MaterialTheme.colors.secondary, RoundedCornerShape(24.dp)),
            )

            Row(
                modifier = Modifier.align(Alignment.Center),
                horizontalArrangement = spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier =
                        Modifier
                            .pointerHoverIcon(PointerIcon.Hand)
                            .weight(1f, fill = true)
                            .fillMaxWidth()
                            .clickable(
                                onClick = { onScaleSelected(Scale.YEAR_2) },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(true, color = Color.White, radius = 19.dp),
                                role = Role.Button,
                            ).padding(start = 8.dp, bottom = 4.dp, top = 4.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "2Y",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onBackground,
                    )
                }
                Box(
                    modifier =
                        Modifier
                            .pointerHoverIcon(PointerIcon.Hand)
                            .weight(1f, fill = true)
                            .fillMaxWidth()
                            .clickable(
                                onClick = { onScaleSelected(Scale.YEAR) },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(true, color = Color.White, radius = 21.dp),
                                role = Role.Button,
                            ).padding(vertical = 4.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "1Y",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onBackground,
                    )
                }
                Box(
                    modifier =
                        Modifier
                            .pointerHoverIcon(PointerIcon.Hand)
                            .weight(1f, fill = true)
                            .fillMaxWidth()
                            .clickable(
                                onClick = { onScaleSelected(Scale.MONTH_6) },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(true, color = Color.White, radius = 19.dp),
                                role = Role.Button,
                            ).padding(end = 8.dp, top = 4.dp, bottom = 4.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = "6M",
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onBackground,
                    )
                }
            }
        }
    }
}
