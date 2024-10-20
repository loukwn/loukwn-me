package com.loukwn.biocompose.presentation.design_system.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import loukwn_me_kotlin_wasm.composeapp.generated.resources.Res
import loukwn_me_kotlin_wasm.composeapp.generated.resources.ostrich_regular
import org.jetbrains.compose.resources.Font
import org.jetbrains.skiko.loadBytesFromPath

@Composable
fun BioTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(typography = BioTypography(), content = content)
}

@Composable
private fun BioTypography(): Typography {
    val Ostrich = FontFamily(
        Font(Res.font.ostrich_regular),
    )

    return Typography(
        h1 = TextStyle(
            fontFamily = Ostrich,
            fontSize = 38.sp,
            fontWeight = FontWeight.Normal
        ),
    )
}