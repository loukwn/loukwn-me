package com.loukwn.biocompose.presentation.portfolio

import com.arkivanov.decompose.ComponentContext

interface PortfolioComponent

class DefaultPortfolioComponent(
    componentContext: ComponentContext
): PortfolioComponent, ComponentContext by componentContext