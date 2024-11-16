package com.loukwn.biocompose.presentation.aboutthis

import com.arkivanov.decompose.ComponentContext

interface AboutThisComponent

class DefaultAboutThisComponent(
    componentContext: ComponentContext
): AboutThisComponent, ComponentContext by componentContext