package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.coroutines.coroutineScope
import com.loukwn.biocompose.data.Date
import com.loukwn.biocompose.data.diffIn6MonthsWith
import com.loukwn.biocompose.data.durationIn6Months
import com.loukwn.biocompose.data.durationString
import com.loukwn.biocompose.data.myJobs
import com.loukwn.biocompose.data.myProjects
import com.loukwn.biocompose.getCurrentYear
import com.loukwn.biocompose.presentation.util.update
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.abs

interface PortfolioComponent {
    val state: State<PortfolioUiState>

    fun onScaleChanged(scale: Scale)
    fun onFilterButtonPressed()
    fun onPageChanged(pageIndex: Int)
    fun onCalendarItemSelected(item: CalendarItem?)
}

class DefaultPortfolioComponent(
    componentContext: ComponentContext,
    private val canGoBackStateFlow: MutableStateFlow<Boolean>,
    deepBackEventDispatchFlow: SharedFlow<Unit>,
) : PortfolioComponent, ComponentContext by componentContext {
    private val _state = mutableStateOf(getInitialState())
    override val state: State<PortfolioUiState> = _state

    private val scope = coroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    init {
        scope.launch {
            delay(400)
            val initialScale = Scale.YEAR

            _state.update {
                it.copy(
                    baseGap = initialScale.baseGap,
                    timeLabels = getTimeLabelsForScale(initialScale),
                    isFilterButtonVisible = true,
                )
            }
        }
        scope.launch {
            deepBackEventDispatchFlow.collectLatest {
                onCalendarItemSelected(null)
            }
        }
    }

    private fun getInitialState(): PortfolioUiState {
        return PortfolioUiState(
            baseGap = 0.dp,
            isFilterButtonVisible = false,
            isCalendarScaleComponentVisible = false,
            timeLabels = emptyList(),
            showCalendarItemDetails = false,
            calendarItems = getCalendarItems(),
            projects = myProjects,
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

    private fun getCalendarItems(): List<CalendarItem> {
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
                    company = job.company,
                    location = job.location,
                    accentColor = job.accentColor,
                    description = job.description,
                    durationText = job.durationString(),
                    links = job.links,
                    size = job.durationIn6Months(),
                )
            )
            if (index != myJobs.lastIndex) {
                jobs.add(
                    CalendarItem.Gap(
                        job.ended.diffIn6MonthsWith(jobsSortedByStartDate[index + 1].started)
                    )
                )
            }
        }
        jobs.add(CalendarItem.Gap(gapFromEnd))

        return jobs.reversed()
    }

    override fun onScaleChanged(scale: Scale) {
        _state.update {
            it.copy(
                baseGap = scale.baseGap,
                timeLabels = getTimeLabelsForScale(scale),
            )
        }
    }

    override fun onFilterButtonPressed() {
        _state.update {
            it.copy(
                isCalendarScaleComponentVisible = !state.value.isCalendarScaleComponentVisible
            )
        }
    }

    override fun onPageChanged(pageIndex: Int) {
        _state.update {
            it.copy(
                isCalendarScaleComponentVisible = false,
                isFilterButtonVisible = pageIndex == 0
            )
        }
    }

    override fun onCalendarItemSelected(item: CalendarItem?) {
        _state.update {
            it.copy(
                showCalendarItemDetails = item != null
            )
        }
        canGoBackStateFlow.update { item == null }
    }

    companion object {
        private const val STARTING_YEAR = 2017
    }
}
