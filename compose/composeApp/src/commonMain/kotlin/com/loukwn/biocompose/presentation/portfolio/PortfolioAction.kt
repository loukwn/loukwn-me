package com.loukwn.biocompose.presentation.portfolio

sealed interface PortfolioAction {
    data class ScaleChange(
        val scale: Scale,
    ) : PortfolioAction

    data object FilterButtonPress : PortfolioAction

    data class PageChange(
        val pageIndex: Int,
    ) : PortfolioAction

    data class CalendarItemSelect(
        val item: CalendarItem?,
    ) : PortfolioAction
}
