package com.loukwn.biocompose.presentation.aboutthis

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.loukwn.biocompose.data.myArtAttributions

interface AboutThisComponent {
    val state: State<AboutThisUiState>
}

class DefaultAboutThisComponent(
    componentContext: ComponentContext
): AboutThisComponent, ComponentContext by componentContext {

    private val _state = mutableStateOf(getInitialState())
    override val state: State<AboutThisUiState> = _state

    private fun getInitialState(): AboutThisUiState {
        return AboutThisUiState(
            artAttributions = myArtAttributions
        )
    }
}