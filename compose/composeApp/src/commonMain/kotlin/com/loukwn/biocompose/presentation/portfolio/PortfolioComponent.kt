package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.ComponentContext
import com.loukwn.biocompose.getCurrentYear
import com.loukwn.biocompose.presentation.util.update

interface PortfolioComponent {
    val state: State<PortfolioUiState>

    fun onScaleChange(scale: Scale)
}

enum class Scale(val baseGap: Dp) {
    YEAR_2(25.dp), YEAR(50.dp), MONTH_6(100.dp)
}

data class PortfolioUiState(
    val baseGap: Dp,
    val timeLabels: List<String>,
)

class DefaultPortfolioComponent(
    componentContext: ComponentContext
) : PortfolioComponent, ComponentContext by componentContext {
    private val _state = mutableStateOf(getInitialState())
    override val state: State<PortfolioUiState> = _state

    private fun getInitialState(): PortfolioUiState {
        return PortfolioUiState(
            baseGap = 0.dp,
            timeLabels = getTimeLabelsForScale(Scale.YEAR),
        )
    }

    private fun getTimeLabelsForScale(scale: Scale): List<String> {
        val currentYear = getCurrentYear()
        val yearsToDisplay = currentYear - STARTING_YEAR + 2

        return when (scale) {
            Scale.YEAR_2 -> {
                buildList {
                    repeat(yearsToDisplay) {
                        add((it + STARTING_YEAR).toString())
                    }
                }.mapIndexed { index, label ->
                    when {
                        index % 2 == 0 && label == (currentYear + 1).toString() -> listOf(label)
                        index % 2 == 0 && label == currentYear.toString() -> listOf(label)
                        index % 2 == 0 -> listOf(label, "")
                        else -> listOf("", "")
                    }
                }
                    .flatten()
                    .reversed()
            }
            Scale.YEAR -> {
                buildList {
                    repeat(yearsToDisplay) {
                        add((it + STARTING_YEAR).toString())
                        add("")
                    }
                }.dropLast(1)
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