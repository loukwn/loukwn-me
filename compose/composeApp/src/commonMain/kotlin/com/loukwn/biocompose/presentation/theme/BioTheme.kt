package com.loukwn.biocompose.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.skiko.loadBytesFromPath

@Composable
fun BioTheme(
    content: @Composable () -> Unit
) {
    val defaultTypography = MaterialTheme.typography
    var typography by remember { mutableStateOf(defaultTypography) }

    LaunchedEffect(Unit) {
        typography = getTypography()
    }

    MaterialTheme(typography = typography, content = content)
}

private suspend fun getTypography(): Typography {
    val Ostrich = FontFamily(
        Font(
            "unique_font_name",
            loadBytesFromPath("font/ostrich_regular.ttf")
        ),
    )

    return Typography(
        h1 = TextStyle(
            fontFamily = Ostrich,
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold,
        ),
    )
}