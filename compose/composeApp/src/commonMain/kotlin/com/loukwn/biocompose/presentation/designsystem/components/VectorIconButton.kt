package com.loukwn.biocompose.presentation.designsystem.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon

@Composable
fun VectorIconButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tintColor: Color = MaterialTheme.colors.onBackground,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick, modifier = modifier.pointerHoverIcon(PointerIcon.Hand)) {
        Icon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = tintColor,
        )
    }
}
