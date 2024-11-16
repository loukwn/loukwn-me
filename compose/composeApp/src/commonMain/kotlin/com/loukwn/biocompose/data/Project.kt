package com.loukwn.biocompose.data

import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.SimpleIcons
import compose.icons.simpleicons.Android
import compose.icons.simpleicons.Github
import compose.icons.simpleicons.Kotlin

data class Project(
    val title: String,
    val description: String,
    val technologies: List<ImageVector>,
    val links: List<IconLink>,
)

val myProjects by lazy {
    listOf(
        Project(
            title ="StageStepBar",
            description = "A library I created that assists in the creation of custom progress bars with stages and configurable steps between these stages. It is available as both a traditional Android View and a Composable.",
            technologies = listOf(SimpleIcons.Android, SimpleIcons.Kotlin),
            links = listOf(
                IconLink(SimpleIcons.Github, "https://github.com/loukwn/StageStepBar")
            )
        ),
        Project(
            title ="GifSound It",
            description = "Combine GIFs with sound.\n\nMy Kotlin workspace where I try to experiment with things like new architectures, libraries, software patterns etc. Currently archived due to Reddit API changes but could continue working on it at some point.",
            technologies = listOf(SimpleIcons.Android, SimpleIcons.Kotlin),
            links = listOf(
                IconLink(SimpleIcons.Github, "https://github.com/loukwn/GifSound-It")
            )
        ),
    )
}