package com.loukwn.biocompose.data

import kotlinx.browser.window

object ScreenLogger {
    @OptIn(ExperimentalWasmJsInterop::class)
    fun logScreen(name: String) {
        window.parent.postMessage(name.toJsString(), "*")
    }
}
