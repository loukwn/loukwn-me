package com.loukwn.biocompose.presentation.designsystem.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MyIcons.BatteryTwoThirds: ImageVector
    get() {
        if (_BatteryTwoThirds != null) {
            return _BatteryTwoThirds!!
        }
        _BatteryTwoThirds =
            ImageVector
                .Builder(
                    name = "BatteryTwoThirds",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 960f,
                    viewportHeight = 960f,
                ).apply {
                    path(fill = SolidColor(Color(0xFF000000))) {
                        moveTo(320f, 880f)
                        quadTo(303f, 880f, 291.5f, 868.5f)
                        quadTo(280f, 857f, 280f, 840f)
                        lineTo(280f, 200f)
                        quadTo(280f, 183f, 291.5f, 171.5f)
                        quadTo(303f, 160f, 320f, 160f)
                        lineTo(400f, 160f)
                        lineTo(400f, 80f)
                        lineTo(560f, 80f)
                        lineTo(560f, 160f)
                        lineTo(640f, 160f)
                        quadTo(657f, 160f, 668.5f, 171.5f)
                        quadTo(680f, 183f, 680f, 200f)
                        lineTo(680f, 840f)
                        quadTo(680f, 857f, 668.5f, 868.5f)
                        quadTo(657f, 880f, 640f, 880f)
                        lineTo(320f, 880f)
                        close()
                        moveTo(360f, 400f)
                        lineTo(600f, 400f)
                        lineTo(600f, 240f)
                        lineTo(360f, 240f)
                        lineTo(360f, 400f)
                        close()
                    }
                }.build()

        return _BatteryTwoThirds!!
    }

@Suppress("ObjectPropertyName")
private var _BatteryTwoThirds: ImageVector? = null
