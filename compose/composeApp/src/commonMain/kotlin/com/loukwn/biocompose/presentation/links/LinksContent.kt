package com.loukwn.biocompose.presentation.links

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun LinksContent(
    component: LinksComponent,
    onSystemUiModeChanged: (isLight: Boolean) -> Unit,
    onBackPressed: () -> Unit
) {
    Text("Links", color = Color.White)
}