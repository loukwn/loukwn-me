package com.loukwn.biocompose.presentation.designsystem.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MyIcons.Home: ImageVector
    get() {
        if (_Home != null) {
            return _Home!!
        }
        _Home =
            ImageVector
                .Builder(
                    name = "Home",
                    defaultWidth = 20.dp,
                    defaultHeight = 20.dp,
                    viewportWidth = 960f,
                    viewportHeight = 960f,
                ).apply {
                    path(
                        fill = SolidColor(Color(0xFF000000)),
                        fillAlpha = 0.9f,
                    ) {
                        moveTo(480f, 880f)
                        quadTo(397f, 880f, 324f, 848.5f)
                        quadTo(251f, 817f, 197f, 763f)
                        quadTo(143f, 709f, 111.5f, 636f)
                        quadTo(80f, 563f, 80f, 480f)
                        quadTo(80f, 397f, 111.5f, 324f)
                        quadTo(143f, 251f, 197f, 197f)
                        quadTo(251f, 143f, 324f, 111.5f)
                        quadTo(397f, 80f, 480f, 80f)
                        quadTo(563f, 80f, 636f, 111.5f)
                        quadTo(709f, 143f, 763f, 197f)
                        quadTo(817f, 251f, 848.5f, 324f)
                        quadTo(880f, 397f, 880f, 480f)
                        quadTo(880f, 563f, 848.5f, 636f)
                        quadTo(817f, 709f, 763f, 763f)
                        quadTo(709f, 817f, 636f, 848.5f)
                        quadTo(563f, 880f, 480f, 880f)
                        close()
                    }
                }.build()

        return _Home!!
    }

@Suppress("ObjectPropertyName")
private var _Home: ImageVector? = null
