package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
        return PortfolioUiState(
            baseGap = Scale.YEAR_2.baseGap,
            timeLabels = getTimeLabelsForScale(Scale.YEAR_2),
            calendarItems = getCalendarItems(),
        )
    }

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
        val gapFromStart = abs(jobsSortedByStartDate.first().started.diffIn6MonthsWith(Date(month = 1, year = STARTING_YEAR)))
        val gapFromEnd = abs(jobsSortedByStartDate.last().ended.diffIn6MonthsWith(Date(month = 12, year = getCurrentYear())))

        jobs.add(CalendarItem.Gap(gapFromStart))
        jobsSortedByStartDate.forEachIndexed { index, job ->
            jobs.add(CalendarItem.Job(job.title, job.durationIn6Months()))
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
                timeLabels = getTimeLabelsForScale(scale),
            )
        }
    }

    companion object {
        private const val STARTING_YEAR = 2017
    }
}


data class Job(
    val title: String,
    val started: Date,
    val ended: Date,
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
            ended = Date(month = getCurrentMonth(), year = getCurrentYear())
        ),
        Job(
            title = "Muzz",
            started = Date(month = 10, year = 2019),
            ended = Date(month = 3, year = 2023)
        ),
        Job(
            title = "Nup",
            started = Date(month = 1, year = 2017),
            ended = Date(month = 1, year = 2019),
        )
    )
}

fun Job.durationIn6Months(): Float {
    return started.diffIn6MonthsWith(ended)
}