package com.loukwn.biocompose.presentation.designsystem.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MyIcons.Back: ImageVector
    get() {
        if (_Back != null) {
            return _Back!!
        }
        _Back =
            ImageVector
                .Builder(
                    name = "Back",
                    defaultWidth = 18.dp,
                    defaultHeight = 18.dp,
                    viewportWidth = 12f,
                    viewportHeight = 14f,
                ).apply {
                    path(
                        fill = SolidColor(Color(0xFFFFFFFF)),
                        fillAlpha = 0.9f,
                        pathFillType = PathFillType.EvenOdd,
                    ) {
                        moveTo(10.4842f, 0.146115f)
                        curveTo(11.1507f, -0.255119f, 12f, 0.224897f, 12f, 1.00285f)
                        verticalLineTo(12.9973f)
                        curveTo(12f, 13.7752f, 11.1507f, 14.2553f, 10.4842f, 13.854f)
                        lineTo(0.522151f, 7.8568f)
                        curveTo(-0.123505f, 7.46812f, -0.123506f, 6.53202f, 0.522151f, 6.14333f)
                        lineTo(10.4842f, 0.146115f)
                        close()
                    }
                }.build()

        return _Back!!
    }

@Suppress("ObjectPropertyName")
private var _Back: ImageVector? = null
