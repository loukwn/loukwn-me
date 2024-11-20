package com.loukwn.biocompose.data

import kotlinx.browser.window

object ScreenLogger {
    fun logScreen(name: String) {
        window.parent.postMessage(name.toJsString(), "*")
    }
}