package com.loukwn.biocompose.presentation.aboutme

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.loukwn.biocompose.getAgeInYears
import com.loukwn.biocompose.presentation.design_system.components.SystemUiGradientOverlay
import com.loukwn.biocompose.presentation.root.GlobalInsetsToConsume
import kotlinx.coroutines.delay
import loukwn_me_kotlin_wasm.composeapp.generated.resources.Res
import loukwn_me_kotlin_wasm.composeapp.generated.resources.dafni
import loukwn_me_kotlin_wasm.composeapp.generated.resources.equipment
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

private const val BottomDrawerAnimationDelayMs = 1000L
private val MoreContentBottomScrimSize = GlobalInsetsToConsume.calculateBottomPadding() + 50.dp

@Composable
fun FakeBottomDrawer(windowHeightDp: Dp) {
    var finishedFirstAnimation by remember { mutableStateOf(false) }

    var targetHeight by remember { mutableStateOf(0.dp) }
    val animatedHeight by animateDpAsState(
        targetHeight,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    LaunchedEffect(windowHeightDp) {
        if (finishedFirstAnimation) {
            targetHeight = windowHeightDp / 4
        }
    }

    LaunchedEffect(Unit) {
        delay(BottomDrawerAnimationDelayMs)
        targetHeight = windowHeightDp / 4
        finishedFirstAnimation = true
    }

    Box(modifier = Modifier.height(animatedHeight).fillMaxWidth())
}

@Composable
fun BottomDrawerList(
    state: AboutMeUiState,
    windowHeightDp: Dp,
    scrollState: ScrollState,
) {
    var finishedFirstAnimation by remember { mutableStateOf(false) }

    var targetPaddingTop by remember { mutableStateOf(windowHeightDp) }
    val animatingPaddingTop by animateDpAsState(
        targetPaddingTop,
        spring(dampingRatio = Spring.DampingRatioMediumBouncy)
    )

    LaunchedEffect(windowHeightDp) {
        if (finishedFirstAnimation) {
            targetPaddingTop = (3 * windowHeightDp) / 4 - 20.dp
        }
    }

    LaunchedEffect(Unit) {
        delay(BottomDrawerAnimationDelayMs)
        targetPaddingTop = (3 * windowHeightDp) / 4 - 20.dp
        finishedFirstAnimation = true
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(animatingPaddingTop))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                    .background(MaterialTheme.colors.background)
                    .padding(
                        top = 32.dp,
                        start = 32.dp,
                        end = 32.dp,
                        bottom = MoreContentBottomScrimSize
                    )
            ) {
                BottomDrawerContent(state)
            }
        }

        if (finishedFirstAnimation) {
            SystemUiGradientOverlay(endColor = MaterialTheme.colors.background)
        }
    }
}

@Composable
private fun ColumnScope.BottomDrawerContent(state: AboutMeUiState) {
    Text(
        text = "Konstantinos, ${getAgeInYears()}",
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.onBackground,
    )

    Spacer(modifier = Modifier.height(40.dp))

    TagSection(
        tags = state.tags,
        modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally),
    )

    Separator(withDivider = true)

    PhraseSection(
        phrase = state.phrase,
        modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally),
    )

    Separator(withDivider = false)

    ImageSection(
        drawable = Res.drawable.equipment,
        text = "My setup",
        modifier = Modifier.align(Alignment.CenterHorizontally),
    )

    Separator(withDivider = false)

    TechnologiesSection(
        entries = state.technologyEntries,
        modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally),
    )

    Separator(withDivider = true)

    Hobbies(
        hobbies = state.hobbies,
        modifier = Modifier.fillMaxSize().align(Alignment.CenterHorizontally),
    )

    Separator(withDivider = false)

    ImageSection(
        drawable = Res.drawable.dafni,
        text = "My village",
        modifier = Modifier.align(Alignment.CenterHorizontally),
    )

    Separator(withDivider = false)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TagSection(tags: List<Tag>, modifier: Modifier = Modifier) {
    FlowRow(
        modifier = modifier,
        verticalArrangement = spacedBy(8.dp),
        horizontalArrangement = spacedBy(8.dp),
    ) {
        for (tag in tags) {
            Row(
                modifier = Modifier
                    .background(MaterialTheme.colors.surface, RoundedCornerShape(20.dp))
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = tag.image,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onSurface,
                )
                Text(
                    text = tag.text,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Composable
private fun ColumnScope.Separator(withDivider: Boolean) {
    Spacer(modifier = Modifier.height(52.dp))
    if (withDivider) {
        Divider(
            modifier = Modifier.align(Alignment.CenterHorizontally)
                .fillMaxWidth(.5f),
            color = MaterialTheme.colors.onBackground.copy(alpha = .3f),
        )
        Spacer(modifier = Modifier.height(52.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TechnologiesSection(
    entries: List<TechonologyEntry>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = spacedBy(20.dp),
    ) {
        Text(
            text = "Favourite technologies",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2,
        )
        FlowRow(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = spacedBy(8.dp),
            horizontalArrangement = spacedBy(8.dp),
        ) {
            entries.forEach {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = it.image,
                    contentDescription = it.contentDescription,
                    tint = MaterialTheme.colors.onBackground,
                )
            }
        }
    }
}


@Composable
private fun ImageSection(
    drawable: DrawableResource,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = spacedBy(4.dp),
    ) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(20.dp))
        )
        Text(
            text = text,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onBackground,
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Hobbies(
    hobbies: List<String>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = spacedBy(20.dp)
    ) {
        Text(
            text = "Hobbies",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2,
        )
        FlowRow(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = spacedBy(8.dp),
            horizontalArrangement = spacedBy(8.dp),
        ) {
            for (hobby in hobbies) {
                Text(
                    modifier = Modifier
                        .background(MaterialTheme.colors.surface, RoundedCornerShape(20.dp))
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    text = hobby,
                    color = MaterialTheme.colors.onSurface,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Composable
private fun PhraseSection(phrase: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = spacedBy(20.dp)
    ) {
        Text(
            text = "A phrase that I often use",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground,
        )
        Text(
            text = phrase,
            style = MaterialTheme.typography.h3,
            color = MaterialTheme.colors.onBackground,
        )
    }
}
