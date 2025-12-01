package com.loukwn.biocompose.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import com.loukwn.biocompose.presentation.phone.PhoneViewModel
import com.loukwn.biocompose.presentation.portfolio.PortfolioViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.reflect.KClass

private val flowModule by lazy {
    object : FlowModule {
        // Aware that these are now effectively Singleton.
        // TODO refactor them to be more nice (or use Koin)
        val canGoBackStateFlow by lazy {
            MutableStateFlow(true)
        }

        val deepBackEventDispatchFlow by lazy {
            MutableSharedFlow<Unit>()
        }

        override fun canGoBackStateFlow(): MutableStateFlow<Boolean> = canGoBackStateFlow

        override fun deepBackEventDispatchFlow(): MutableSharedFlow<Unit> = deepBackEventDispatchFlow
    }
}

private val viewModelResolverModule by lazy {
    object : ViewModelResolverModule {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> createViewModel(clazz: KClass<T>): T =
            when (clazz) {
                PhoneViewModel::class -> viewModelModule.phoneViewModel() as T
                PortfolioViewModel::class -> viewModelModule.portfolioViewModel() as T
                else -> throw IllegalArgumentException("Unknown ViewModel class")
            }
    }
}

private val viewModelModule by lazy {

    object : ViewModelModule {
        override fun phoneViewModel(): PhoneViewModel =
            PhoneViewModel(
                flowModule.canGoBackStateFlow,
                flowModule.deepBackEventDispatchFlow,
            )

        override fun portfolioViewModel(): PortfolioViewModel =
            PortfolioViewModel(
                flowModule.canGoBackStateFlow(),
                flowModule.deepBackEventDispatchFlow(),
            )
    }
}

private interface FlowModule {
    fun canGoBackStateFlow(): MutableStateFlow<Boolean>

    fun deepBackEventDispatchFlow(): MutableSharedFlow<Unit>
}

private interface ViewModelResolverModule {
    fun <T : ViewModel> createViewModel(clazz: KClass<T>): T
}

private interface ViewModelModule {
    fun phoneViewModel(): PhoneViewModel

    fun portfolioViewModel(): PortfolioViewModel
}

// This is added so it helps make everything else private in this file (due to inline on the viewModel()`
fun <T : ViewModel> createViewModel(clazz: KClass<T>): T = viewModelResolverModule.createViewModel(clazz)

@Composable
inline fun <reified T : ViewModel> viewModel(): T = remember { createViewModel(T::class) }
