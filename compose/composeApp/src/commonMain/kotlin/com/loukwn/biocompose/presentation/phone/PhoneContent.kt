package com.loukwn.biocompose.presentation.phone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loukwn.biocompose.data.ScreenLogger
import com.loukwn.biocompose.presentation.aboutme.AboutMeScreen
import com.loukwn.biocompose.presentation.aboutthis.AboutThisScreen
import com.loukwn.biocompose.presentation.designsystem.theme.LoukwnMeTheme
import com.loukwn.biocompose.presentation.desktop.DesktopApp
import com.loukwn.biocompose.presentation.desktop.DesktopScreen
import com.loukwn.biocompose.presentation.links.LinksScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.serialization.Serializable

@Composable
fun PhoneContent() {
    val navController = rememberNavController()
    val rootViewModel = viewModel { RootViewModel() }
    val state by rootViewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        rootViewModel.commands.collectLatest { command ->
            when (command) {
                is PhoneCommand.GoBack -> {
                    navController.popBackStack()
                }
                is PhoneCommand.NavigateTo -> {
                    navController.navigate(command.destination)
                }
            }
        }
    }

    PhoneContentInternal(
        navController = navController,
        state = state,
        onSystemUiModeChanged = rootViewModel::onSystemUiModeChanged,
        onDesktopAppPressed = rootViewModel::onDesktopAppPressed,
        onBackButtonPressed = rootViewModel::onBackButtonPressed,
        onHomeButtonPressed = rootViewModel::onHomeButtonPressed,
    )
}

@Composable
private fun PhoneContentInternal(
    navController: NavHostController,
    state: PhoneUiState,
    onSystemUiModeChanged: (isLight: Boolean) -> Unit,
    onDesktopAppPressed: (DesktopApp) -> Unit,
    onBackButtonPressed: () -> Unit,
    onHomeButtonPressed: () -> Unit,
) {
    LoukwnMeTheme {
        Box(modifier = Modifier.background(Color.Black)) {
            NavGraph(
                navController = navController,
                onSystemUiModeChanged = onSystemUiModeChanged,
                onDesktopAppPressed = onDesktopAppPressed,
                onBackButtonPressed = onBackButtonPressed,
            )
            StatusBar(
                modifier = Modifier.fillMaxWidth(),
                time = state.time,
                inLightMode = state.systemUiInLightMode,
            )
            NavigationBar(
                modifier = Modifier.align(Alignment.BottomCenter).fillMaxWidth(),
                inLightMode = state.systemUiInLightMode,
                onBackPressed = onBackButtonPressed,
                onHomePressed = onHomeButtonPressed,
            )
        }
    }
}

sealed interface Destination {
    @Serializable
    data object Desktop : Destination

    @Serializable
    data object AboutMe : Destination

    @Serializable
    data object Portfolio : Destination

    @Serializable
    data object Links : Destination

    @Serializable
    data object AboutThis : Destination
}

@Composable
private fun NavGraph(
    navController: NavHostController,
    onSystemUiModeChanged: (isLight: Boolean) -> Unit,
    onDesktopAppPressed: (DesktopApp) -> Unit,
    onBackButtonPressed: () -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Destination.Desktop,
    ) {
        composable<Destination.Desktop> {
            LaunchedEffect(Unit) {
                ScreenLogger.logScreen("desktop")
            }

            DesktopScreen(
                onSystemUiModeChanged = onSystemUiModeChanged,
                onAppPressed = onDesktopAppPressed,
            )
        }

        composable<Destination.AboutMe> {
            LaunchedEffect(Unit) {
                ScreenLogger.logScreen("about_me")
            }

            AboutMeScreen(
                onSystemUiModeChanged = onSystemUiModeChanged,
                onBackButtonPressed = onBackButtonPressed,
            )
        }

        composable<Destination.Portfolio> {
            Text(modifier = Modifier.fillMaxSize().background(Color.White), text = "Portfolio")
        }

        composable<Destination.Links> {
            LaunchedEffect(Unit) {
                ScreenLogger.logScreen("links")
            }

            LinksScreen(onBackButtonPressed)
        }

        composable<Destination.AboutThis> {
            LaunchedEffect(Unit) {
                ScreenLogger.logScreen("about_this")
            }

            AboutThisScreen(onBackButtonPressed)
        }
    }
}
