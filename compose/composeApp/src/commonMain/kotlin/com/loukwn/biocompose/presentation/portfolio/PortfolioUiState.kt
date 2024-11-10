package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

enum class Scale(val baseGap: Dp) {
    YEAR_2(25.dp), YEAR(50.dp), MONTH_6(100.dp)
}

data class PortfolioUiState(
    val baseGap: Dp,
    val isFilterButtonVisible: Boolean,
    val isCalendarScaleComponentVisible: Boolean,
    val timeLabels: List<String>,
    val calendarItems: List<CalendarItem>,
)

sealed class CalendarItem {
    abstract val size: Float

    data class Job(
        val title: String,
        val accentColor: Color,
        val durationText: String,
        override val size: Float,
    ): CalendarItem()

    data class Gap(
        override val size: Float,
    ): CalendarItem()
}
