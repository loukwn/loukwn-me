package com.loukwn.biocompose.presentation.util

import androidx.compose.runtime.MutableState

fun <T> MutableState<T>.update(block: (T) -> T) {
    value = block(value)
}