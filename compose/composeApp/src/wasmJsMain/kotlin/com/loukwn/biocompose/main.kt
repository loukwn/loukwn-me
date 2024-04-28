package com.loukwn.biocompose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.CanvasBasedWindow
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.arkivanov.essenty.lifecycle.stop
import com.arkivanov.essenty.statekeeper.StateKeeperDispatcher
import com.loukwn.biocompose.presentation.root.DefaultRootComponent
import com.loukwn.biocompose.presentation.root.RootContent
import com.loukwn.biocompose.presentation.util.decodeSerializableContainer
import com.loukwn.biocompose.presentation.util.encodeToString
import kotlinx.browser.document
import kotlinx.browser.localStorage
import kotlinx.browser.window
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.w3c.dom.Document
import org.w3c.dom.get
import org.w3c.dom.set

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()
    val coroutineScope = CoroutineScope(SupervisorJob())
    val stateKeeper = StateKeeperDispatcher(savedState = localStorage[KEY_SAVED_STATE]?.decodeSerializableContainer())

    val root =
        DefaultRootComponent(
            componentContext = DefaultComponentContext(lifecycle = lifecycle, stateKeeper = stateKeeper),
            coroutineScope = coroutineScope,
        )

    lifecycle.attachToDocument()

    window.onbeforeunload =
        {
            localStorage[KEY_SAVED_STATE] = stateKeeper.save().encodeToString()
            null
        }

    // TODO: Take the title from resources after https://youtrack.jetbrains.com/issue/KT-49981
    CanvasBasedWindow(title = "Compose Bio", canvasElementId = "ComposeTarget") {
        DisposableEffect(Unit) {
            onDispose {
                coroutineScope.cancel()
            }
        }
        RootContent(root, modifier = Modifier.fillMaxSize())
    }
}

private const val KEY_SAVED_STATE = "saved_state"

private fun LifecycleRegistry.attachToDocument() {
    fun onVisibilityChanged() {
        if (visibilityState(document) == "visible") {
            resume()
        } else {
            stop()
        }
    }

    onVisibilityChanged()

    document.addEventListener(type = "visibilitychange", callback = { onVisibilityChanged() })
}

// Workaround for Document#visibilityState not available in Wasm
@JsFun("(document) => document.visibilityState")
private external fun visibilityState(document: Document): String
