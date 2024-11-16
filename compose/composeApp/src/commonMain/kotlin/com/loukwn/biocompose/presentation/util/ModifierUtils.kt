package com.loukwn.biocompose.presentation.util

import androidx.compose.ui.Modifier

fun Modifier.modifyIf(
    predicate: Boolean,
    modification: Modifier.() -> (Modifier)
): Modifier {
    return if (predicate) {
        this.then(modification())
    } else {
        this
    }
}