package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class Scale(val baseGap: Dp) {
    YEAR_2(25.dp), YEAR(50.dp), MONTH_6(100.dp)
}

data class PortfolioUiState(
    val baseGap: Dp,
//    val currentDateLineOffset: Dp,
    val timeLabels: List<String>,
    val calendarItems: List<List<CalendarItem>>,
)

sealed class CalendarItem {
    abstract val size: Float

    data class Job(
        val title: String,
        val durationText: String,
        override val size: Float,
    ): CalendarItem()

    data class Gap(
        override val size: Float,
    ): CalendarItem()
}

data class PortfolioJob(
    val title: String,
    val monthStarted: Int,
    val yearStarted: Int,
)