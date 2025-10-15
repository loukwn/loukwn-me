package com.loukwn.biocompose.presentation.aboutthis

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.loukwn.biocompose.data.ArtAttribution
import com.loukwn.biocompose.data.Constants
import com.loukwn.biocompose.presentation.designsystem.components.SystemUiGradientOverlay
import com.loukwn.biocompose.presentation.designsystem.components.VectorIconButton
import com.loukwn.biocompose.presentation.phone.GlobalInsetsToConsume
import compose.icons.EvaIcons
import compose.icons.SimpleIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.Outline
import compose.icons.evaicons.fill.ArrowDown
import compose.icons.evaicons.fill.Info
import compose.icons.evaicons.outline.ArrowIosBack
import compose.icons.simpleicons.Github

@Composable
fun AboutThisContent(
    component: AboutThisComponent,
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
                    ).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = spacedBy(32.dp),
        ) {
            TopBar(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, bottom = 16.dp),
                onBackButtonPressed = onBackPressed,
            )
            Title()
            ViewSource()
            Description()
            Attributions(state.artAttributions)
            WebsiteVersion()

            Spacer(modifier = Modifier.height(60.dp))
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
    }
}

@Composable
private fun Title() {
    Column(
        verticalArrangement = spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier =
                Modifier
                    .size(72.dp)
                    .clip(CircleShape)
                    .background(Color(0xff39343f))
                    .border(1.dp, MaterialTheme.colors.onBackground, CircleShape),
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = EvaIcons.Fill.Info,
                contentDescription = null,
                tint = MaterialTheme.colors.onBackground,
            )
        }

        Text(
            text = "About This",
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.h1,
        )
    }
}

@Composable
private fun ViewSource() {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier =
            Modifier
                .pointerHoverIcon(PointerIcon.Hand)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.Black)
                .clickable {
                    uriHandler.openUri("https://github.com/loukwn/loukwn-me")
                }.padding(20.dp),
        verticalArrangement = spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            imageVector = SimpleIcons.Github,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
        )
        Text("View source", color = MaterialTheme.colors.onBackground)
    }
}

@Composable
private fun Description() {
    Text(
        "This is a Compose Multiplatform (WASM) app that runs inside an iframe of a simple HTML/JS static page. There is some basic communication between the two (using messages) so that backgrounds change when a \"mobile app\" is opened. For more info check out the GitHub repo above.",
        color = MaterialTheme.colors.onBackground,
    )
}

@Composable
private fun Attributions(artAttribution: List<ArtAttribution>) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { expanded = !expanded },
    ) {
        Text("Attributions", color = MaterialTheme.colors.onBackground)

        val targetRotation = if (expanded) 180f else 0f
        val iconRotation by animateFloatAsState(targetRotation)

        Icon(
            imageVector = EvaIcons.Fill.ArrowDown,
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground,
            modifier = Modifier.rotate(iconRotation),
        )
    }

    AnimatedVisibility(
        expanded,
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically(),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 20.dp),
        ) {
            Text(
                "Photos",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground,
            )
            Spacer(modifier = Modifier.height(20.dp))

            artAttribution.forEach { artAttribution ->
                val annotatedText =
                    buildAnnotatedString {
                        val text = "Photo by ${artAttribution.artistName} on Unsplash"
                        append(text)

                        val artistLinkStart = text.indexOf(artAttribution.artistName)
                        val artistLinkEnd = artistLinkStart + artAttribution.artistName.length
                        addLink(
                            url = LinkAnnotation.Url(artAttribution.artistLink),
                            start = artistLinkStart,
                            end = artistLinkEnd,
                        )
                        addStyle(
                            style =
                                SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = Color(0xff479aff),
                                ),
                            start = artistLinkStart,
                            end = artistLinkEnd,
                        )

                        val artLinkStart = text.indexOf("Unsplash")
                        val artLinkEnd = artLinkStart + "Unsplash".length
                        addLink(
                            url = LinkAnnotation.Url(artAttribution.artLink),
                            start = artLinkStart,
                            end = artLinkEnd,
                        )
                        addStyle(
                            style =
                                SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = Color(0xff479aff),
                                ),
                            start = artLinkStart,
                            end = artLinkEnd,
                        )
                    }
                Text(annotatedText, color = MaterialTheme.colors.onBackground)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Icons",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground,
            )
            Spacer(modifier = Modifier.height(20.dp))

            val iconText =
                buildAnnotatedString {
                    val text =
                        "Icons used throughout the app come from Eva Icons, LineAwesome Icons and Simple Icons. Special thanks to the folks behind these awesome projects!"
                    append(text)

                    listOf(
                        "Eva Icons" to "https://akveo.github.io/eva-icons/#/",
                        "LineAwesome Icons" to "https://icons8.com/line-awesome",
                        "Simple Icons" to "https://simpleicons.org/",
                    ).forEach { (name, link) ->
                        val start = text.indexOf(name)
                        val end = start + name.length
                        addLink(
                            url = LinkAnnotation.Url(link),
                            start = start,
                            end = end,
                        )
                        addStyle(
                            style =
                                SpanStyle(
                                    textDecoration = TextDecoration.Underline,
                                    color = Color(0xff479aff),
                                ),
                            start = start,
                            end = end,
                        )
                    }
                }

            Text(iconText, color = MaterialTheme.colors.onBackground)

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Logos",
                style = MaterialTheme.typography.h1,
                color = MaterialTheme.colors.onBackground,
            )
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                "Logos of GitHub, Medium, LinkedIn, Google Play, Android, Godot and any other copyrighted ones I have used in the app are licensed trademarks of their respective owners. I do not own any of these.",
                color = MaterialTheme.colors.onBackground,
            )
        }
    }
}

@Composable
private fun WebsiteVersion() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        Text("v${Constants.APP_VERSION}", color = Color.White)
    }
}
