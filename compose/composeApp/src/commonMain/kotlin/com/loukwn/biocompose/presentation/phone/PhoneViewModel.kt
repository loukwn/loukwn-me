package com.loukwn.biocompose.presentation.phone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.loukwn.biocompose.getFormattedTime
import com.loukwn.biocompose.presentation.desktop.DesktopApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RootViewModel : ViewModel() {
    private val _state = MutableStateFlow(PhoneUiState(getFormattedTime(), false))
    val state: StateFlow<PhoneUiState> = _state.asStateFlow()

    private val _commands = MutableSharedFlow<PhoneCommand>()
    val commands: SharedFlow<PhoneCommand> = _commands

    private val canGoBackStateFlow = MutableStateFlow(true)
    private val deepBackEventDispatchFlow = MutableSharedFlow<Unit>()

    init {
        updateTimeInIntervals()
    }

    private fun updateTimeInIntervals() {
        viewModelScope.launch {
            while (true) {
                ensureActive()
                delay(TIME_FETCH_INTERVAL_MS)
                _state.value = _state.value.copy(time = getFormattedTime())
            }
        }
    }

    fun onBackButtonPressed() =
        viewModelScope.launch {
            if (canGoBackStateFlow.value) {
                _commands.emit(PhoneCommand.GoBack)
            } else {
                deepBackEventDispatchFlow.emit(Unit)
            }
        }

    fun onHomeButtonPressed() =
        viewModelScope.launch {
            _commands.emit(PhoneCommand.GoBack)
            canGoBackStateFlow.update { true }
        }

    fun onDesktopAppClicked(desktopApp: DesktopApp) =
        viewModelScope.launch {
            val destination =
                when (desktopApp) {
                    DesktopApp.AboutMe -> Destination.AboutMe
                    DesktopApp.Portfolio -> Destination.Portfolio
                    DesktopApp.Links -> Destination.Links
                    DesktopApp.AboutThis -> Destination.AboutThis
                }
            _commands.emit(PhoneCommand.NavigateTo(destination))
        }

    fun onSystemUiModeChanged(isLight: Boolean) {
        _state.update { it.copy(systemUiInLightMode = isLight) }
    }

    companion object {
        private const val TIME_FETCH_INTERVAL_MS = 1000L
    }
}

data class PhoneUiState(
    val time: String,
    val systemUiInLightMode: Boolean,
)
