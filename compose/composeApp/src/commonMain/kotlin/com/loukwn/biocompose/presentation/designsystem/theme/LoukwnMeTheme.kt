package com.loukwn.biocompose.presentation.designsystem.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import loukwn_me_kotlin_wasm.composeapp.generated.resources.Res
import loukwn_me_kotlin_wasm.composeapp.generated.resources.ostrich_regular
import org.jetbrains.compose.resources.Font

@Composable
fun LoukwnMeTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = LoukwnMeTypography(),
        colors = LoukwnMeColors(),
        content = content,
    )
}

@Composable
private fun LoukwnMeColors(): Colors =
    MaterialTheme.colors.copy(
        primary = Color(0xff39343f),
        secondary = Color(0xff81758f),
        background = Color(0xff1b1a20),
        surface = Color.Gray.copy(alpha = .1f),
        onPrimary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White,
    )

@Composable
private fun LoukwnMeTypography(): Typography {
    val ostrichFontFamily =
        FontFamily(
            Font(Res.font.ostrich_regular),
        )

    return Typography(
        h1 =
            TextStyle(
                fontFamily = ostrichFontFamily,
                fontSize = 38.sp,
                fontWeight = FontWeight.Normal,
            ),
        h2 =
            TextStyle(
                fontFamily = ostrichFontFamily,
                fontSize = 36.sp,
                fontWeight = FontWeight.Normal,
                letterSpacing = 3.sp,
            ),
        h3 =
            MaterialTheme.typography.h3.copy(
                lineHeight = 30.sp,
                fontSize = 24.sp,
            ),
        subtitle1 =
            MaterialTheme.typography.subtitle1.copy(
                fontSize = 20.sp,
            ),
        body2 =
            MaterialTheme.typography.body2.copy(
                fontSize = 14.sp,
                lineHeight = 22.sp,
            ),
        caption =
            MaterialTheme.typography.caption.copy(
                fontSize = 12.sp,
                lineHeight = 22.sp,
            ),
    )
}
