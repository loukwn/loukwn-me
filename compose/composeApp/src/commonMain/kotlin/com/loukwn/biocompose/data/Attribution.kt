package com.loukwn.biocompose.data

data class ArtAttribution(
    val artistName: String,
    val artistLink: String,
    val artLink: String,
)

val myArtAttributions by lazy {
    listOf(
        ArtAttribution(
            artistName = "NEW DATA SERVICES",
            artistLink = "https://unsplash.com/@new_data_services?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/s/photos/notes?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        ArtAttribution(
            artistName = "Cynthia Young",
            artistLink = "https://unsplash.com/@cynthiayoung?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/@cynthiayoung?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        ArtAttribution(
            artistName = "Kilian Seiler",
            artistLink = "https://unsplash.com/@kilianfoto?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/@kilianfoto?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        ArtAttribution(
            artistName = "Mathyas Kurmann",
            artistLink = "https://unsplash.com/@mathyaskurmann?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/s/visual/6950cac1-76d6-4c60-9b00-5cb96fb48dcc?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        ArtAttribution(
            artistName = "Sean Lim",
            artistLink = "https://unsplash.com/@seanlimm?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/s/visual/e076f4a1-6404-4ea7-949c-8cd55f39fc63?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        ArtAttribution(
            artistName = "Max van den Oetelaar",
            artistLink = "https://unsplash.com/@maxvdo?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/s/visual/2672bb5c-a940-475e-b35c-59a5b12deef6?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        ArtAttribution(
            artistName = "Brian Patrick Tagalog",
            artistLink = "https://unsplash.com/@briantagalog?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/s/visual/509ee80a-8273-4541-bc2d-8e0ed4a688a1?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),
        ArtAttribution(
            artistName = "Daniel Romero",
            artistLink = "https://unsplash.com/@rmrdnl?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
            artLink = "https://unsplash.com/s/photos/android?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText",
        ),

        )
}