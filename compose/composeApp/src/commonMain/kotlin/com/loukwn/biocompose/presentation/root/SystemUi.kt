package com.loukwn.biocompose.presentation.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loukwn.biocompose.presentation.design_system.icons.Back
import com.loukwn.biocompose.presentation.design_system.icons.BatteryTwoThirds
import com.loukwn.biocompose.presentation.design_system.icons.Home
import com.loukwn.biocompose.presentation.design_system.icons.MyIcons
import com.loukwn.biocompose.presentation.design_system.icons.NetworkCell
import com.loukwn.biocompose.presentation.design_system.icons.NetworkWifi
import com.loukwn.biocompose.presentation.design_system.icons.Recents

private const val StatusBarHeightDp = 32
private const val StatusBarVerticalPaddingDp = 4
private const val NavigationBarHeightDp = 48

val GlobalInsetsToConsume = PaddingValues(
    top = StatusBarHeightDp.dp,
    bottom = NavigationBarHeightDp.dp,
    start = 0.dp,
    end = 0.dp,
)

@Composable
fun StatusBar(modifier: Modifier, time: String, inLightMode: Boolean = true) {
    val foregroundTintColor = if (inLightMode) {
        Color.Black
    } else {
        Color.White
    }

    Row(
        modifier = modifier
            .height(StatusBarHeightDp.dp)
            .padding(vertical = StatusBarVerticalPaddingDp.dp, horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = time,
            color = foregroundTintColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Row(modifier = Modifier.fillMaxHeight()) {
            val iconSizeDp = (StatusBarHeightDp - 2 * StatusBarVerticalPaddingDp).dp

            Image(
                imageVector = MyIcons.NetworkWifi,
                modifier = Modifier.size(iconSizeDp),
                contentDescription = null,
                colorFilter = ColorFilter.tint(foregroundTintColor)
            )
            Image(
                imageVector = MyIcons.NetworkCell,
                modifier = Modifier.size(iconSizeDp),
                contentDescription = null,
                colorFilter = ColorFilter.tint(foregroundTintColor)
            )
            Image(
                imageVector = MyIcons.BatteryTwoThirds,
                modifier = Modifier.size(iconSizeDp),
                contentDescription = null,
                colorFilter = ColorFilter.tint(foregroundTintColor)
            )
        }
    }
}

@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    inLightMode: Boolean,
    onBackPressed: () -> Unit,
    onHomePressed: () -> Unit,
) {
    val foregroundTintColor = if (inLightMode) {
        Color.Black
    } else {
        Color.White
    }

    Row(
        modifier = modifier
            .height(NavigationBarHeightDp.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ) {
        NavigationBarButton(MyIcons.Back, foregroundTintColor, onBackPressed)
        NavigationBarButton(MyIcons.Home, foregroundTintColor, onHomePressed)
        NavigationBarButton(MyIcons.Recents, foregroundTintColor) { }
    }
}

@Composable
private fun NavigationBarButton(
    imageVector: ImageVector,
    tintColor: Color,
    onClick: () -> Unit,
) {

    val hoverInteractionSource = remember { MutableInteractionSource() }
    val isHovered by hoverInteractionSource.collectIsHoveredAsState()
    val tint = if (isHovered) tintColor.copy(alpha = .5f) else tintColor

    Row(
        modifier = Modifier
            .fillMaxHeight()
            .width(100.dp)
            .clip(RoundedCornerShape(24.dp))
            .hoverable(hoverInteractionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(true, color = Color.White),
                role = Role.Button,
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = null,
            colorFilter = ColorFilter.tint(tint)
        )
    }
}
