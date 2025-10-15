package com.loukwn.biocompose.presentation.designsystem.icons

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val MyIcons.Recents: ImageVector
    get() {
        if (_Recents != null) {
            return _Recents!!
        }
        _Recents =
            ImageVector
                .Builder(
                    name = "Recents",
                    defaultWidth = 16.dp,
                    defaultHeight = 16.dp,
                    viewportWidth = 14f,
                    viewportHeight = 14f,
                ).apply {
                    path(
                        fill = SolidColor(Color(0xFF797979)),
                        fillAlpha = 0.9f,
                    ) {
                        moveTo(2f, 0f)
                        horizontalLineTo(12f)
                        arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 14f, 2f)
                        verticalLineTo(12f)
                        arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 12f, 14f)
                        horizontalLineTo(2f)
                        arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 12f)
                        verticalLineTo(2f)
                        arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 0f)
                        close()
                    }
                }.build()

        return _Recents!!
    }

@Suppress("ObjectPropertyName")
private var _Recents: ImageVector? = null
