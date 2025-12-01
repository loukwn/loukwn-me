package com.loukwn.biocompose.data

import kotlinx.browser.window

object ScreenLogger {
    @OptIn(ExperimentalWasmJsInterop::class)
    fun logScreen(screen: ScreenLog) {
        window.parent.postMessage(screen.logId.toJsString(), "*")
    }
}

enum class ScreenLog(
    val logId: String,
) {
    Desktop("home"),
    AboutMe("about-me"),
    Portfolio("portfolio"),
    Links("contact-me"),
    AboutThis("about-this"),
}
