package com.loukwn.biocompose.presentation.aboutme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.loukwn.biocompose.presentation.root.GlobalInsetsToConsume

@Composable
fun AboutMeContent(component: AboutMeComponent, onSystemUiModeChanged: (isLight: Boolean) -> Unit) {
    LaunchedEffect(Unit) {
        onSystemUiModeChanged(true)
    }

    Column(Modifier.padding(GlobalInsetsToConsume)) {
        Text("About me")
    }
}