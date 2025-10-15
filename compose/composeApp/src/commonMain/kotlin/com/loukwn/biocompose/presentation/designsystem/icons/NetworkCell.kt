package com.loukwn.biocompose.presentation.designsystem.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MyIcons.NetworkCell: ImageVector
    get() {
        if (_NetworkCell != null) {
            return _NetworkCell!!
        }
        _NetworkCell =
            ImageVector
                .Builder(
                    name = "NetworkCell",
                    defaultWidth = 24.dp,
                    defaultHeight = 24.dp,
                    viewportWidth = 960f,
                    viewportHeight = 960f,
                ).apply {
                    path(fill = SolidColor(Color(0xFF000000))) {
                        moveTo(80f, 880f)
                        lineTo(880f, 80f)
                        lineTo(880f, 880f)
                        lineTo(80f, 880f)
                        close()
                        moveTo(680f, 800f)
                        lineTo(800f, 800f)
                        lineTo(800f, 274f)
                        lineTo(680f, 394f)
                        lineTo(680f, 800f)
                        close()
                    }
                }.build()

        return _NetworkCell!!
    }

@Suppress("ObjectPropertyName")
private var _NetworkCell: ImageVector? = null
