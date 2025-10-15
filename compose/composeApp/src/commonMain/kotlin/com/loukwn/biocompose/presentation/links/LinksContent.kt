package com.loukwn.biocompose.presentation.links

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.loukwn.biocompose.data.FullLink
import com.loukwn.biocompose.presentation.designsystem.components.SystemUiGradientOverlay
import com.loukwn.biocompose.presentation.designsystem.components.VectorIconButton
import com.loukwn.biocompose.presentation.root.GlobalInsetsToConsume
import com.loukwn.biocompose.presentation.util.modifyIf
import compose.icons.EvaIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.ArrowIosBack
import compose.icons.evaicons.outline.Navigation2

@Composable
fun LinksContent(
    component: LinksComponent,
    onBackPressed: () -> Unit,
) {
    val state by remember { component.state }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.background)
                    .padding(
                        top = GlobalInsetsToConsume.calculateTopPadding(),
                        bottom = 0.dp,
                        start = 36.dp,
                        end = 36.dp,
                    ),
        ) {
            TopBar(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                onBackButtonPressed = onBackPressed,
            )
            LinksList(
                state = state,
                onLinkSelected = component::onLinkSelected,
            )
        }
        SystemUiGradientOverlay()
    }
}

@Composable
private fun TopBar(
    modifier: Modifier = Modifier,
    onBackButtonPressed: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        VectorIconButton(
            imageVector = EvaIcons.Outline.ArrowIosBack,
            onClick = onBackButtonPressed,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Links",
            style = MaterialTheme.typography.h1,
            color = Color.White,
            modifier = Modifier.weight(1f),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LinksList(
    state: LinksUiState,
    onLinkSelected: (FullLink) -> Unit,
) {
    LazyColumn(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 16.dp, bottom = 120.dp),
    ) {
        state.groupedLinks.forEach { (initial, linksForInitial) ->
            stickyHeader {
                Text(initial.uppercaseChar().toString(), color = Color.White)
            }

            items(linksForInitial) { link ->
                LinksListItem(
                    link = link,
                    isSelected = link == state.selectedLink,
                    onLinkSelected = onLinkSelected,
                )
            }
        }
    }
}

@Composable
private fun LinksListItem(
    link: FullLink,
    isSelected: Boolean,
    onLinkSelected: (FullLink) -> Unit,
) {
    val targetPadding = if (isSelected) 4.dp else 0.dp
    val paddingAnimated by animateDpAsState(targetPadding)

    val selectedBg = MaterialTheme.colors.primary

    Column(
        modifier =
            Modifier
                .padding(horizontal = paddingAnimated)
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .modifyIf(isSelected) {
                    background(selectedBg)
                }.pointerHoverIcon(PointerIcon.Hand)
                .clickable { onLinkSelected(link) },
    ) {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
            horizontalArrangement = spacedBy(40.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = link.icon,
                contentDescription = null,
                tint = link.iconColor,
                modifier = Modifier.background(link.bgColor, shape = CircleShape).padding(16.dp),
            )
            Text(link.displayText, color = Color.White)
        }
        AnimatedVisibility(isSelected) {
            val uriHandler = LocalUriHandler.current
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, bottom = 12.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .clickable(
                            onClick = { uriHandler.openUri(link.url) },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(true, color = Color.White),
                            role = Role.Button,
                        ).padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(link.displayUrl, color = Color.White)

                Icon(
                    imageVector = EvaIcons.Outline.Navigation2,
                    contentDescription = "Navigate to link",
                    tint = MaterialTheme.colors.onBackground,
                )
            }
        }
    }
}
