package com.loukwn.biocompose.presentation.aboutthis

import androidx.lifecycle.ViewModel
import com.loukwn.biocompose.data.myArtAttributions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AboutThisViewModel : ViewModel() {
    private val _state = MutableStateFlow(getInitialState())
    val state: StateFlow<AboutThisUiState> = _state.asStateFlow()

    private fun getInitialState(): AboutThisUiState =
        AboutThisUiState(
            artAttributions = myArtAttributions,
        )
}
