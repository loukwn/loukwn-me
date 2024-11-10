package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.arkivanov.decompose.ComponentContext
import com.loukwn.biocompose.getCurrentMonth
import com.loukwn.biocompose.getCurrentYear
import com.loukwn.biocompose.presentation.util.update
import kotlin.math.abs

interface PortfolioComponent {
    val state: State<PortfolioUiState>

    fun onScaleChange(scale: Scale)
}

class DefaultPortfolioComponent(
    componentContext: ComponentContext
) : PortfolioComponent, ComponentContext by componentContext {
    private val _state = mutableStateOf(getInitialState())
    override val state: State<PortfolioUiState> = _state

    private fun getInitialState(): PortfolioUiState {
        val initialScale = Scale.MONTH_6

        return PortfolioUiState(
            baseGap = initialScale.baseGap,
//            currentDateLineOffset = getCurrentDateLineOffset(initialScale),
            timeLabels = getTimeLabelsForScale(initialScale),
            calendarItems = getCalendarItems(),
        )
    }

//    private fun getCurrentDateLineOffset(scale: Scale): Dp {
//        val numberOfMonthsTillNextYear = (getCurrentYear() + 1) * 12 - (getCurrentYear() * 12 + getCurrentMonth())
//        return when (scale) {
//            Scale.YEAR_2 -> numberOfMonthsTillNextYear * 100 / 24f
//            Scale.YEAR -> numberOfMonthsTillNextYear * 100 / 12f
//            Scale.MONTH_6 -> numberOfMonthsTillNextYear * 100 / 6f
//        }.dp
//    }

    private fun getTimeLabelsForScale(scale: Scale): List<String> {
        val currentYear = getCurrentYear()
        val yearsToDisplay = currentYear - STARTING_YEAR + 2

        return when (scale) {
            Scale.YEAR_2 -> {
                buildList {
                    repeat(yearsToDisplay) {
                        if (it % 2 == 0) {
                            add((it + STARTING_YEAR).toString())
                        }
                    }
                }.map {
                    if (it.toInt() == currentYear) {
                        listOf("", "", "", it, "", "")
                    } else {
                        listOf("", "", "", it)
                    }
                }
                    .flatten()
                    .reversed()
            }

            Scale.YEAR -> {
                buildList {
                    repeat(yearsToDisplay) {
                        add("")
                        add((it + STARTING_YEAR).toString())
                    }
                }
                    .reversed()
            }

            Scale.MONTH_6 -> {
                buildList {
                    repeat((yearsToDisplay) * 2 - 1) {
                        val year = it / 2 + STARTING_YEAR
                        if (it % 2 == 0) {
                            add("Jan $year")
                        } else {
                            add("Jun $year")
                        }
                    }
                }
                    .reversed()
            }
        }
    }

    private fun getCalendarItems(): List<List<CalendarItem>> {
        val jobs = mutableListOf<CalendarItem>()

        val jobsSortedByStartDate = myJobs.sortedBy { it.started.year * 12 + it.started.month }
        val gapFromStart = abs(
            jobsSortedByStartDate.first().started.diffIn6MonthsWith(
                Date(
                    month = 1,
                    year = STARTING_YEAR
                )
            )
        )
        val gapFromEnd = abs(
            jobsSortedByStartDate.last().ended.diffIn6MonthsWith(
                Date(
                    month = 12,
                    year = getCurrentYear()
                )
            )
        )

        jobs.add(CalendarItem.Gap(gapFromStart))
        jobsSortedByStartDate.forEachIndexed { index, job ->
            jobs.add(
                CalendarItem.Job(
                    title = job.title,
                    accentColor = job.accentColor,
                    durationText = job.durationString(),
                    size = job.durationIn6Months(),
                )
            )
            if (index != myJobs.lastIndex) {
                jobs.add(CalendarItem.Gap(job.ended.diffIn6MonthsWith(jobsSortedByStartDate[index + 1].started)))
            }
        }
        jobs.add(CalendarItem.Gap(gapFromEnd))


        return listOf(jobs.reversed())
    }

    override fun onScaleChange(scale: Scale) {
        _state.update {
            it.copy(
                baseGap = scale.baseGap,
//                currentDateLineOffset = getCurrentDateLineOffset(scale),
                timeLabels = getTimeLabelsForScale(scale),
            )
        }
    }

    companion object {
        private const val STARTING_YEAR = 2019
    }
}


data class Job(
    val title: String,
    val started: Date,
    val ended: Date,
    val accentColor: Color,
)

data class Date(
    val month: Int,
    val year: Int,
)

fun Date.diffIn6MonthsWith(other: Date): Float {
    val startMonth = year * 12 + month
    val endMonth = other.year * 12 + other.month
    return (endMonth - startMonth) / 6f
}

val myJobs by lazy {
    listOf(
        Job(
            title = "Nutmeg",
            started = Date(month = 3, year = 2023),
            ended = Date(month = getCurrentMonth(), year = getCurrentYear()),
            accentColor = Color(0xff0060f0)
        ),
        Job(
            title = "Muzz",
            started = Date(month = 10, year = 2019),
            ended = Date(month = 3, year = 2023),
            accentColor = Color(0xfffb406c)
        ),
    )
}

fun Job.durationIn6Months(): Float {
    return started.diffIn6MonthsWith(ended)
}

fun Job.durationString(): String {
    val startPart = "${getMonthNameForNumber(started.month)} ${started.year}"
    val endPart = if (ended.year == getCurrentYear() && ended.month == getCurrentMonth()) {
        "Present"
    } else {
        "${getMonthNameForNumber(ended.month)} ${ended.year}"
    }
    return "$startPart - $endPart"
}

fun getMonthNameForNumber(month: Int): String {
    return when (month) {
        1 -> "Jan"
        2 -> "Feb"
        3 -> "Mar"
        4 -> "Apr"
        5 -> "May"
        6 -> "Jun"
        7 -> "Jul"
        8 -> "Aug"
        9 -> "Sep"
        10 -> "Oct"
        11 -> "Nov"
        else -> "Dec"
    }
}