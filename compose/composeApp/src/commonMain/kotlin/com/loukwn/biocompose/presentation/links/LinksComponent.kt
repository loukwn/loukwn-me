package com.loukwn.biocompose.presentation.links

import com.arkivanov.decompose.ComponentContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow

interface LinksComponent

class DefaultLinksComponent(
    componentContext: ComponentContext,
    private val canGoBackStateFlow: MutableStateFlow<Boolean>,
    deepBackEventDispatchFlow: SharedFlow<Unit>,
): LinksComponent, ComponentContext by componentContext