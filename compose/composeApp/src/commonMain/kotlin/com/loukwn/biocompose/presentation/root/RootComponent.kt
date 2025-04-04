package com.loukwn.biocompose.presentation.root

import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import com.arkivanov.decompose.value.Value
import com.loukwn.biocompose.getFormattedTime
import com.loukwn.biocompose.presentation.aboutme.AboutMeComponent
import com.loukwn.biocompose.presentation.aboutme.DefaultAboutMeComponent
import com.loukwn.biocompose.presentation.aboutthis.AboutThisComponent
import com.loukwn.biocompose.presentation.aboutthis.DefaultAboutThisComponent
import com.loukwn.biocompose.presentation.desktop.DefaultDesktopComponent
import com.loukwn.biocompose.presentation.desktop.DesktopApp
import com.loukwn.biocompose.presentation.desktop.DesktopComponent
import com.loukwn.biocompose.presentation.links.DefaultLinksComponent
import com.loukwn.biocompose.presentation.links.LinksComponent
import com.loukwn.biocompose.presentation.portfolio.DefaultPortfolioComponent
import com.loukwn.biocompose.presentation.portfolio.PortfolioComponent
import com.loukwn.biocompose.presentation.util.update
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Stable
interface RootComponent {

    val stack: Value<ChildStack<*, Child>>
    val state: State<RootUiState>

    fun onBack()
    fun onHome()
    fun onDesktopAppClicked(desktopApp: DesktopApp)
    fun onSystemUiModeChanged(isLight: Boolean)

    sealed class Child {
        data class Desktop(val component: DesktopComponent) : Child()
        data class AboutMe(val component: AboutMeComponent) : Child()
        data class Portfolio(val component: PortfolioComponent) : Child()
        data class Links(val component: LinksComponent) : Child()
        data class AboutThis(val component: AboutThisComponent) : Child()
    }
}

data class RootUiState(
    val time: String,
    val systemUiInLightMode: Boolean,
)

@Stable
class DefaultRootComponent(
    componentContext: ComponentContext,
    private val coroutineScope: CoroutineScope,
) : RootComponent, ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()

    private val _state = mutableStateOf(RootUiState(getFormattedTime(), false))
    override val state: State<RootUiState> = _state

    private val canGoBackStateFlow = MutableStateFlow(true)
    private val deepBackEventDispatchFlow = MutableSharedFlow<Unit>()

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
            is Configuration.AboutMe -> RootComponent.Child.AboutMe(aboutMeComponent(componentContext))
            is Configuration.Portfolio -> RootComponent.Child.Portfolio(portfolioComponent(componentContext))
            is Configuration.Links -> RootComponent.Child.Links(linksComponent(componentContext))
            is Configuration.AboutThis -> RootComponent.Child.AboutThis(aboutThisComponent(componentContext))
        }

    private fun desktopComponent(componentContext: ComponentContext): DesktopComponent =
        DefaultDesktopComponent(componentContext)

    private fun aboutMeComponent(componentContext: ComponentContext): AboutMeComponent =
        DefaultAboutMeComponent(componentContext)
    
    private fun portfolioComponent(componentContext: ComponentContext): PortfolioComponent =
        DefaultPortfolioComponent(componentContext, canGoBackStateFlow, deepBackEventDispatchFlow)

    private fun linksComponent(componentContext: ComponentContext): LinksComponent =
        DefaultLinksComponent(componentContext)

    private fun aboutThisComponent(componentContext: ComponentContext): AboutThisComponent =
        DefaultAboutThisComponent(componentContext)

    override fun onBack() {
        if (canGoBackStateFlow.value) {
            navigation.pop()
        } else {
            coroutineScope.launch {
                deepBackEventDispatchFlow.emit(Unit)
            }
        }
    }

    override fun onHome() {
        navigation.pop()
        canGoBackStateFlow.update { true }
    }

    override fun onDesktopAppClicked(desktopApp: DesktopApp) {
        val configuration = when (desktopApp) {
            DesktopApp.AboutMe -> Configuration.AboutMe
            DesktopApp.Portfolio -> Configuration.Portfolio
            DesktopApp.Links -> Configuration.Links
            DesktopApp.AboutThis -> Configuration.AboutThis
        }
        navigation.push(configuration)
    }

    override fun onSystemUiModeChanged(isLight: Boolean) {
        _state.update { it.copy(systemUiInLightMode = isLight) }
    }

    @Serializable
    private sealed interface Configuration {
        @Serializable
        data object Desktop : Configuration

        @Serializable
        data object AboutMe : Configuration
        
        @Serializable
        data object Portfolio : Configuration

        @Serializable
        data object Links : Configuration

        @Serializable
        data object AboutThis : Configuration
    }

    companion object {
        private const val TIME_FETCH_INTERVAL_MS = 1000L
    }
}