package com.loukwn.biocompose.presentation.phone

sealed interface PhoneCommand {
    data class NavigateTo(
        val destination: Destination,
    ) : PhoneCommand

    data object GoBack : PhoneCommand
}
