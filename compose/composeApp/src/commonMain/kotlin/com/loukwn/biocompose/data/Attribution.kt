package com.loukwn.biocompose.data

data class ArtAttribution(
    val artistName: String,
    val artistLink: String,
    val artLink: String,
)

val myArtAttributions by lazy {
    listOf(
        ArtAttribution(
            artistName = "Nick Nice",
            artistLink = "https://unsplash.com/@nicknice?utm_content=creditCopyText&utm_medium=referral&utm_source=unsplash",
            artLink = "https://unsplash.com/photos/grayscale-photo-of-stone-gPm8h3DS1s4",
        ),
        ArtAttribution(
            artistName = "Kilian Seiler",
            artistLink = "https://unsplash.com/@kilianfoto?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/photos/person-repairing-smartphones-under-a-lighted-table-PZLgTUAhxMM",
        ),
        ArtAttribution(
            artistName = "Mathyas Kurmann",
            artistLink = "https://unsplash.com/@mathyaskurmann?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/photos/six-assorted-color-mail-boxes-fb7yNPbT0l8",
        ),
        ArtAttribution(
            artistName = "Sean Lim",
            artistLink = "https://unsplash.com/@seanlimm?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/photos/black-laptop-computer-displaying-blue-screen-NPlv2pkYoUA",
        ),
        ArtAttribution(
            artistName = "Max van den Oetelaar",
            artistLink = "https://unsplash.com/@maxvdo?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/photos/underwater-caves-during-daytime-F3rDBnQQbQU",
        ),
        ArtAttribution(
            artistName = "Brian Patrick Tagalog",
            artistLink = "https://unsplash.com/@briantagalog?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/photos/grayscale-photography-of-rock-formation-_8hGFBxWD0A",
        ),
    )
}