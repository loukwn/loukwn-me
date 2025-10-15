package com.loukwn.biocompose.presentation.links

import androidx.lifecycle.ViewModel
import com.loukwn.biocompose.data.FullLink
import com.loukwn.biocompose.data.ScreenLogger
import com.loukwn.biocompose.data.myLinks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LinksViewModel : ViewModel() {
    private val _state = MutableStateFlow(getInitialState())
    val state: StateFlow<LinksUiState> = _state.asStateFlow()

    init {
        ScreenLogger.logScreen("contact_me")
    }

    private fun getInitialState(): LinksUiState =
        LinksUiState(
            groupedLinks = myLinks.sortedBy { it.displayText }.groupBy { it.displayText[0] },
            selectedLink = null,
        )

    fun onLinkSelected(link: FullLink) {
        _state.update {
            val selection = if (link == it.selectedLink) null else link
            it.copy(selectedLink = selection)
        }
    }
}
