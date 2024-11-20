package com.loukwn.biocompose.presentation.design_system.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MyIcons.NetworkWifi: ImageVector
    get() {
        if (_NetworkWifi != null) {
            return _NetworkWifi!!
        }
        _NetworkWifi = ImageVector.Builder(
            name = "NetworkWifi",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(480f, 840f)
                lineTo(0f, 360f)
                quadTo(95f, 263f, 219.5f, 211.5f)
                quadTo(344f, 160f, 480f, 160f)
                quadTo(617f, 160f, 741f, 211f)
                quadTo(865f, 262f, 960f, 360f)
                lineTo(480f, 840f)
                close()
                moveTo(174f, 420f)
                quadTo(241f, 372f, 319f, 346f)
                quadTo(397f, 320f, 480f, 320f)
                quadTo(563f, 320f, 641f, 346f)
                quadTo(719f, 372f, 786f, 420f)
                lineTo(844f, 362f)
                quadTo(765f, 302f, 672f, 271f)
                quadTo(579f, 240f, 480f, 240f)
                quadTo(381f, 240f, 288f, 271f)
                quadTo(195f, 302f, 116f, 362f)
                lineTo(174f, 420f)
                close()
            }
        }.build()

        return _NetworkWifi!!
    }

@Suppress("ObjectPropertyName")
private var _NetworkWifi: ImageVector? = null
