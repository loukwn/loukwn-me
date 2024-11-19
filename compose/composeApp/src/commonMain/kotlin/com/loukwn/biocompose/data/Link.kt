package com.loukwn.biocompose.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import compose.icons.EvaIcons
import compose.icons.SimpleIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Email
import compose.icons.evaicons.outline.File
import compose.icons.simpleicons.Github
import compose.icons.simpleicons.Linkedin
import compose.icons.simpleicons.Medium

data class IconLink(
    val icon: ImageVector,
    val url: String,
)

data class FullLink(
    val icon: ImageVector,
    val iconColor: Color,
    val displayText: String,
    val url: String,
    val displayUrl: String,
    val bgColor: Color,
)

val myLinks by lazy {
    listOf(
        FullLink(
            icon = SimpleIcons.Linkedin,
            iconColor = Color.White,
            displayText = "LinkedIn",
            url = "https://www.linkedin.com/in/klountzis",
            displayUrl = "linkedin.com/in/klountzis",
            bgColor = Color(0xff0073b1),
        ),
        FullLink(
            icon = SimpleIcons.Medium,
            displayText = "Medium",
            iconColor = Color.Black,
            url = "https://medium.com/@loukwn",
            displayUrl = "medium.com/@loukwn",
            bgColor = Color.White,
        ),
        FullLink(
            icon = SimpleIcons.Github,
            iconColor = Color.White,
            displayText = "GitHub",
            url = "https://github.com/loukwn",
            displayUrl = "github.com/loukwn",
            bgColor = Color.Black,
        ),
        FullLink(
            icon = EvaIcons.Outline.Email,
            iconColor = Color.White,
            displayText = "Email",
            url = "mailto:hello@loukwn.me",
            displayUrl = "hello@loukwn.me",
            bgColor = Color(0xffb94034)
        ),
        FullLink(
            icon = EvaIcons.Outline.File,
            iconColor = Color.Black,
            displayText = "CV / Resume",
            url = "https://web.tresorit.com/l/ZMrXs#rpmuwuaqM40JcbPIObRvAg",
            displayUrl = "My personal CV",
            bgColor = Color(0xfff5e58f),
        ),
    )
}