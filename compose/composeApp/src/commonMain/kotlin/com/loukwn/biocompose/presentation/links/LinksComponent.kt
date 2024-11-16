package com.loukwn.biocompose.presentation.links

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.loukwn.biocompose.data.FullLink
import com.loukwn.biocompose.data.myLinks
import com.loukwn.biocompose.presentation.portfolio.PortfolioUiState
import com.loukwn.biocompose.presentation.util.update
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface LinksComponent {
    val state: State<LinksUiState>

    fun onLinkSelected(link: FullLink)
}

class DefaultLinksComponent(
    componentContext: ComponentContext,
    private val canGoBackStateFlow: MutableStateFlow<Boolean>,
    deepBackEventDispatchFlow: SharedFlow<Unit>,
): LinksComponent, ComponentContext by componentContext {

    private val _state = mutableStateOf(getInitialState())
    override val state: State<LinksUiState> = _state

    private fun getInitialState(): LinksUiState {
        return LinksUiState(
            groupedLinks = myLinks.sortedBy { it.displayText }.groupBy { it.displayText[0] },
            selectedLink = null,
        )
    }

    override fun onLinkSelected(link: FullLink) {
        _state.update {
            val selection = if (link == it.selectedLink) null else link
            it.copy(selectedLink = selection)
        }
    }
}