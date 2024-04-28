package com.loukwn.biocompose.presentation.root

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.loukwn.biocompose.getFormattedTime
import com.loukwn.biocompose.presentation.desktop.DefaultDesktopComponent
import com.loukwn.biocompose.presentation.desktop.DesktopComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

interface RootComponent {

    val stack: Value<ChildStack<*, Child>>
    val state: State<RootUiState>

    fun onBackClicked()

    sealed class Child {
        data class Desktop(val component: DesktopComponent) : Child()
    }
}

data class RootUiState(
    val time: String,
)

class DefaultRootComponent(
    componentContext: ComponentContext,
    private val coroutineScope: CoroutineScope,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    private val _state = mutableStateOf(RootUiState(getFormattedTime()))
    override val state: State<RootUiState> = _state

    init {
        updateTimeInIntervals()
    }

    private fun updateTimeInIntervals() {
        coroutineScope.launch {
            while(isActive) {
                delay(TIME_FETCH_INTERVAL_MS)
                _state.value = _state.value.copy(time = getFormattedTime())
            }
        }
    }

    override val stack: Value<ChildStack<*, RootComponent.Child>> =
        childStack(
            source = navigation,
            serializer = Configuration.serializer(),
            initialConfiguration = Configuration.Desktop,
            handleBackButton = true, // Automatically pop from the stack on back button presses
            childFactory = ::child,
        )

    private fun child(configuration: Configuration, componentContext: ComponentContext): RootComponent.Child =
        when (configuration) {
            is Configuration.Desktop -> RootComponent.Child.Desktop(desktopComponent(componentContext))
        }

    private fun desktopComponent(componentContext: ComponentContext): DesktopComponent =
        DefaultDesktopComponent(componentContext)


    override fun onBackClicked() {

    }

    @Serializable
    private sealed interface Configuration {
        @Serializable
        data object Desktop : Configuration

//        @Serializable
//        data object AboutMe : Configuration
//
//        @Serializable
//        data object Portfolio : Configuration
//
//        @Serializable
//        data object ContactMe : Configuration
//
//        @Serializable
//        data object AboutThis : Configuration
    }

    companion object {
        private const val TIME_FETCH_INTERVAL_MS = 1000L
    }
}