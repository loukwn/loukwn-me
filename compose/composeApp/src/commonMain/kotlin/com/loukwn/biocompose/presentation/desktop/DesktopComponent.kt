package com.loukwn.biocompose.presentation.desktop

import com.arkivanov.decompose.ComponentContext

interface DesktopComponent

class DefaultDesktopComponent(
    componentContext: ComponentContext
): DesktopComponent, ComponentContext by componentContext