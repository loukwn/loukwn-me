package com.loukwn.biocompose.presentation.aboutme

import com.arkivanov.decompose.ComponentContext

interface AboutMeComponent

class DefaultAboutMeComponent(
    componentContext: ComponentContext
): AboutMeComponent, ComponentContext by componentContext