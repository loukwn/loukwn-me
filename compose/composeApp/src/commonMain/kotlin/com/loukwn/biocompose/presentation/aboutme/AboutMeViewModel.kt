package com.loukwn.biocompose.presentation.aboutme

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import com.loukwn.biocompose.data.ScreenLog
import com.loukwn.biocompose.data.ScreenLogger
import compose.icons.LineAwesomeIcons
import compose.icons.SimpleIcons
import compose.icons.lineawesomeicons.AddressCard
import compose.icons.lineawesomeicons.CitySolid
import compose.icons.lineawesomeicons.GlobeEuropeSolid
import compose.icons.lineawesomeicons.MapPinSolid
import compose.icons.simpleicons.Android
import compose.icons.simpleicons.Firefoxbrowser
import compose.icons.simpleicons.Git
import compose.icons.simpleicons.Gnubash
import compose.icons.simpleicons.Godotengine
import compose.icons.simpleicons.Kotlin
import compose.icons.simpleicons.Latex
import compose.icons.simpleicons.Linux
import compose.icons.simpleicons.Python
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AboutMeViewModel : ViewModel() {
    private val phrases =
        listOf(
            "LGTM",
            "Works fine on my machine",
            "Should not take too long to implement",
            "I do not see how this can break in the future",
            "I am pretty sure this crash is impossible to hit",
            "It was working before",
            "Did you try restarting?",
        )

    private val _state = MutableStateFlow(getInitialState())
    val state: StateFlow<AboutMeUiState> = _state.asStateFlow()

    init {
        ScreenLogger.logScreen(ScreenLog.AboutMe)
    }

    private fun getInitialState(): AboutMeUiState {
        val tags =
            listOf(
                Tag(LineAwesomeIcons.AddressCard, "Android / Mobile Engineer"),
                Tag(LineAwesomeIcons.GlobeEuropeSolid, "Greek"),
                Tag(LineAwesomeIcons.CitySolid, "London, UK"),
                Tag(LineAwesomeIcons.MapPinSolid, "At most 12,764.221km away"),
            )

        val phrase = phrases.random()

        val technologyEntries =
            listOf(
                TechonologyEntry(SimpleIcons.Android, "Android"),
                TechonologyEntry(SimpleIcons.Kotlin, "Kotlin"),
                TechonologyEntry(SimpleIcons.Gnubash, "Shell"),
                TechonologyEntry(SimpleIcons.Python, "Python"),
                TechonologyEntry(SimpleIcons.Latex, "Latex"),
                TechonologyEntry(SimpleIcons.Godotengine, "Godot"),
                TechonologyEntry(SimpleIcons.Git, "Git"),
                TechonologyEntry(SimpleIcons.Firefoxbrowser, "Firefox"),
                TechonologyEntry(SimpleIcons.Linux, "Linux"),
            )

        val hobbies =
            listOf(
                "Game dev",
                "Cycling",
                "Listening to music",
                "Playing video games",
                "Doomscrolling",
                "Abandoning my projects",
            )

        return AboutMeUiState(
            tags = tags,
            phrase = phrase,
            technologyEntries = technologyEntries,
            hobbies = hobbies,
        )
    }
}

data class AboutMeUiState(
    val tags: List<Tag>,
    val phrase: String,
    val technologyEntries: List<TechonologyEntry>,
    val hobbies: List<String>,
)

data class TechonologyEntry(
    val image: ImageVector,
    val contentDescription: String,
)

data class Tag(
    val image: ImageVector,
    val text: String,
)
