package com.loukwn.biocompose.data

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.loukwn.biocompose.getCurrentMonth
import com.loukwn.biocompose.getCurrentYear
import compose.icons.EvaIcons
import compose.icons.SimpleIcons
import compose.icons.evaicons.Outline
import compose.icons.evaicons.outline.Link2
import compose.icons.simpleicons.Googleplay
import compose.icons.simpleicons.Linkedin

data class Job(
    val title: String,
    val started: Date,
    val ended: Date,
    val location: String,
    val company: String,
    val description: String,
    val accentColor: Color,
    val links: List<JobLink>,
)

data class JobLink(
    val imageVector: ImageVector,
    val link: String,
)

data class Date(
    val month: Int,
    val year: Int,
)

fun Date.diffIn6MonthsWith(other: Date): Float {
    val startMonth = year * 12 + month
    val endMonth = other.year * 12 + other.month
    return (endMonth - startMonth) / 6f
}

fun Job.durationIn6Months(): Float {
    return started.diffIn6MonthsWith(ended)
}

fun Job.durationString(): String {
    val startPart = "${getMonthNameForNumber(started.month)} ${started.year}"
    val endPart = if (ended.year == getCurrentYear() && ended.month == getCurrentMonth()) {
        "Present"
    } else {
        "${getMonthNameForNumber(ended.month)} ${ended.year}"
    }
    return "$startPart - $endPart"
}

private fun getMonthNameForNumber(month: Int): String {
    return when (month) {
        1 -> "Jan"
        2 -> "Feb"
        3 -> "Mar"
        4 -> "Apr"
        5 -> "May"
        6 -> "Jun"
        7 -> "Jul"
        8 -> "Aug"
        9 -> "Sep"
        10 -> "Oct"
        11 -> "Nov"
        else -> "Dec"
    }
}

val myJobs by lazy {
    listOf(
        Job(
            title = "Android Engineer",
            company = "JPMorgan Chase",
            location = "London, UK",
            description = "Helping people invest their money and secure their future.\n\nCurrently working at Nutmeg as an Android Engineer where I develop initiatives (and lead some of them) as well as actively contribute to the refactoring of the app.",
            started = Date(month = 3, year = 2023),
            ended = Date(month = getCurrentMonth(), year = getCurrentYear()),
            accentColor = Color(0xff005eb8),
            links = listOf(
                JobLink(SimpleIcons.Googleplay, "https://play.google.com/store/apps/details?id=com.nutmeg.app&hl=en_GB"),
                JobLink(EvaIcons.Outline.Link2, "https://www.nutmeg.com/")
            )
        ),
        Job(
            title = "Android Engineer",
            company = "Muzz",
            location = "London, UK",
            description = "Helped millions of Muslims find their special one.\n\nFor more than 3 years I was part of the Muzz Android team, tasked with shipping features that provided a lot of value to the customers, while at the same time focusing on refactoring the app and improving its performance.",
            started = Date(month = 10, year = 2019),
            ended = Date(month = 3, year = 2023),
            accentColor = Color(0xfffb406c),
            links = listOf(
                JobLink(SimpleIcons.Googleplay, "https://play.google.com/store/apps/details?id=com.muzmatch.muzmatchapp&hl=en_GB"),
                JobLink(EvaIcons.Outline.Link2, "https://muzz.com/en-GB/")
            )
        ),
        Job(
            title = "Android Engineer (Part time)",
            company = "UNIpad & Nup",
            location = "Athens, Greece",
            description = "Helped university students throughout their academic journey.\n\nWas part of a small startup that published two social media applications catering both to the studying and socializing aspects of the university student life.",
            started = Date(month = 1, year = 2017),
            ended = Date(month = 1, year = 2019),
            accentColor = Color(0xff1ff9f8),
            links = listOf(
                JobLink(SimpleIcons.Linkedin, "https://www.linkedin.com/company/unipad/"),
                JobLink(SimpleIcons.Googleplay, "https://play.google.com/store/apps/details?id=com.nup.nupdroid&hl=en")
            )
        ),
    )
}