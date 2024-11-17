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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.loukwn.biocompose.getAgeInYears
import com.loukwn.biocompose.presentation.design_system.components.SystemUiGradientOverlay
import com.loukwn.biocompose.presentation.root.GlobalInsetsToConsume
import kotlinx.coroutines.delay
import loukwn_me_kotlin_wasm.composeapp.generated.resources.Res
import loukwn_me_kotlin_wasm.composeapp.generated.resources.dafni
import loukwn_me_kotlin_wasm.composeapp.generated.resources.equipment
import loukwn_me_kotlin_wasm.composeapp.generated.resources.ostrich_regular
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource

private const val BottomDrawerAnimationDelayMs = 1000L
private val MoreContentBottomScrimSize = GlobalInsetsToConsume.calculateBottomPadding() + 50.dp
val AboutMeBottomSheetBgColor = Color(0xff1b1a20)

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
                    .background(AboutMeBottomSheetBgColor)
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
            SystemUiGradientOverlay(endColor = AboutMeBottomSheetBgColor)
        }
    }
}

@Composable
private fun ColumnScope.BottomDrawerContent(
    state: AboutMeUiState,
) {
    val title = buildAnnotatedString {
        withStyle(
            SpanStyle(
                fontFamily = FontFamily(
                    Font(Res.font.ostrich_regular),
                ), fontSize = 40.sp
            )
        ) {
            append("Konstantinos, ${getAgeInYears()}")
        }
    }
    Text(title, fontSize = 32.sp, color = Color.White)

    Spacer(modifier = Modifier.height(40.dp))

    TagSection(state.tags)

    Separator(withDivider = true)

    PhraseSection(state.phrase)

    Separator(withDivider = false)

    ImageSection(
        drawable = Res.drawable.equipment,
        text = "My setup"
    )

    Separator(withDivider = false)

    TechnologiesSection(state.technologyEntries)

    Separator(withDivider = true)

    Hobbies(state.hobbies)

    Separator(withDivider = false)

    ImageSection(
        drawable = Res.drawable.dafni,
        text = "My village"
    )

    Separator(withDivider = false)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TagSection(tagList: List<Tag>) {
    FlowRow(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = spacedBy(8.dp),
        horizontalArrangement = spacedBy(8.dp),
    ) {
        for (tag in tagList) {
            Row(
                modifier = Modifier
                    .background(Color.Gray.copy(alpha = .1f), RoundedCornerShape(20.dp))
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = tag.image,
                    contentDescription = null,
                    tint = Color.White,
                )
                Text(tag.text, color = Color.White, fontSize = 14.sp)
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
                .fillMaxWidth(.5f), color = Color.White.copy(alpha = .3f)
        )
        Spacer(modifier = Modifier.height(52.dp))
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TechnologiesSection(technologyEntries: List<TechonologyEntry>) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("Favourite technologies", fontSize = 14.sp, color = Color.White)
        Spacer(modifier = Modifier.height(20.dp))
        FlowRow(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = spacedBy(8.dp),
            horizontalArrangement = spacedBy(8.dp),
        ) {
            technologyEntries.forEach {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = it.image,
                    contentDescription = it.contentDescription,
                    tint = Color.White,
                )
            }
        }
    }
}


@Composable
private fun ImageSection(
    drawable: DrawableResource,
    text: String,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(drawable),
            contentDescription = null,
            modifier = Modifier.clip(RoundedCornerShape(20.dp))
        )
        Text(
            text = text,
            fontSize = 11.sp,
            color = Color.White,
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun Hobbies(hobbies: List<String>) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("Hobbies", fontSize = 14.sp, color = Color.White)
        Spacer(modifier = Modifier.height(20.dp))
        FlowRow(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = spacedBy(8.dp),
            horizontalArrangement = spacedBy(8.dp),
        ) {
            for (hobby in hobbies) {
                Text(
                    modifier = Modifier
                        .background(Color.Gray.copy(alpha = .1f), RoundedCornerShape(20.dp))
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    text = hobby,
                    color = Color.White,
                    fontSize = 14.sp,
                )
            }
        }
    }
}

@Composable
private fun PhraseSection(phrase: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Text("A phrase that I often use", fontSize = 14.sp, color = Color.White)
        Spacer(modifier = Modifier.height(20.dp))
        Text(phrase, fontSize = 24.sp, color = Color.White)
    }
}
