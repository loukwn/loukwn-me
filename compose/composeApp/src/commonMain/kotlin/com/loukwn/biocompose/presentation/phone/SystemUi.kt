package com.loukwn.biocompose.presentation.phone

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
import com.loukwn.biocompose.presentation.designsystem.icons.Back
import com.loukwn.biocompose.presentation.designsystem.icons.BatteryTwoThirds
import com.loukwn.biocompose.presentation.designsystem.icons.Home
import com.loukwn.biocompose.presentation.designsystem.icons.MyIcons
import com.loukwn.biocompose.presentation.designsystem.icons.NetworkCell
import com.loukwn.biocompose.presentation.designsystem.icons.NetworkWifi
import com.loukwn.biocompose.presentation.designsystem.icons.Recents

private const val STATUS_BAR_HEIGHT_DP = 32
private const val STATUS_BAR_VERTICAL_PADDING_DP = 4
private const val NAVIGATION_BAR_HEIGHT_DP = 48

val GlobalInsetsToConsume =
    PaddingValues(
        top = STATUS_BAR_HEIGHT_DP.dp,
        bottom = NAVIGATION_BAR_HEIGHT_DP.dp,
        start = 0.dp,
        end = 0.dp,
    )

@Composable
fun StatusBar(
    modifier: Modifier,
    time: String,
    inLightMode: Boolean = true,
) {
    val foregroundTintColor =
        if (inLightMode) {
            Color.Black
        } else {
            Color.White
        }

    Row(
        modifier =
            modifier
                .height(STATUS_BAR_HEIGHT_DP.dp)
                .padding(vertical = STATUS_BAR_VERTICAL_PADDING_DP.dp, horizontal = 32.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = time,
            color = foregroundTintColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )
        Row(modifier = Modifier.fillMaxHeight()) {
            val iconSizeDp = (STATUS_BAR_HEIGHT_DP - 2 * STATUS_BAR_VERTICAL_PADDING_DP).dp

            Image(
                imageVector = MyIcons.NetworkWifi,
                modifier = Modifier.size(iconSizeDp),
                contentDescription = null,
                colorFilter = ColorFilter.tint(foregroundTintColor),
            )
            Image(
                imageVector = MyIcons.NetworkCell,
                modifier = Modifier.size(iconSizeDp),
                contentDescription = null,
                colorFilter = ColorFilter.tint(foregroundTintColor),
            )
            Image(
                imageVector = MyIcons.BatteryTwoThirds,
                modifier = Modifier.size(iconSizeDp),
                contentDescription = null,
                colorFilter = ColorFilter.tint(foregroundTintColor),
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
    val foregroundTintColor =
        if (inLightMode) {
            Color.Black
        } else {
            Color.White
        }

    Row(
        modifier =
            modifier
                .height(NAVIGATION_BAR_HEIGHT_DP.dp),
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
        modifier =
            Modifier
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
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = null,
            colorFilter = ColorFilter.tint(tint),
        )
    }
}
