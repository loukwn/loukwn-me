package com.loukwn.biocompose.presentation.root

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import loukwn_me_kotlin_wasm.composeapp.generated.resources.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

private const val StatusBarHeightDp = 32
private const val StatusBarVerticalPaddingDp = 4
private const val NavigationBarHeightDp = 48

val GlobalInsetsToConsume = PaddingValues(
    top = StatusBarHeightDp.dp,
    bottom = NavigationBarHeightDp.dp,
    start = 0.dp,
    end = 0.dp,
)

@OptIn(ExperimentalResourceApi::class)
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
                painterResource(Res.drawable.network_wifi),
                modifier = Modifier.size(iconSizeDp),
                contentDescription = "",
                colorFilter = ColorFilter.tint(foregroundTintColor)
            )
            Image(
                painterResource(Res.drawable.network_cell),
                modifier = Modifier.size(iconSizeDp),
                contentDescription = "",
                colorFilter = ColorFilter.tint(foregroundTintColor)
            )
            Image(
                painterResource(Res.drawable.battery_5_bar_24),
                modifier = Modifier.size(iconSizeDp),
                contentDescription = "",
                colorFilter = ColorFilter.tint(foregroundTintColor)
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun NavigationBar(
    modifier: Modifier = Modifier,
    inLightMode: Boolean,
    onBackClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    onRecentsClicked: () -> Unit,
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
        NavigationBarButton(onBackClicked) {
            Image(
                painterResource(Res.drawable.back),
                contentDescription = "",
                colorFilter = ColorFilter.tint(foregroundTintColor)
            )
        }
        NavigationBarButton(onHomeClicked) {
            Image(
                painterResource(Res.drawable.home),
                contentDescription = "",
                colorFilter = ColorFilter.tint(foregroundTintColor)
            )
        }
        NavigationBarButton(onRecentsClicked) {
            Image(
                painterResource(Res.drawable.recents),
                contentDescription = "",
                colorFilter = ColorFilter.tint(foregroundTintColor)
            )
        }
    }
}

@Composable
private fun NavigationBarButton(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxHeight()
            .width(100.dp)
            .clip(RoundedCornerShape(24.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(true, color = Color.White),
                role = Role.Button,
                onClick = onClick,
            ),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
    }
}
