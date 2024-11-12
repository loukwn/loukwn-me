package com.loukwn.biocompose.data

import androidx.compose.ui.graphics.Color
import com.loukwn.biocompose.getCurrentMonth
import com.loukwn.biocompose.getCurrentYear

data class Job(
    val title: String,
    val started: Date,
    val ended: Date,
    val location: String,
    val company: String,
    val description: String,
    val accentColor: Color,
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
            description = "I work at asd asdg asdhasd ahsd asdasd sdag \n\nasdg asdfha sdhasdhasd hasdasd ahsdhaeas asdh asdh g\n\n asdgasdg asdga sdasdhasdhasdh",
            started = Date(month = 3, year = 2023),
            ended = Date(month = getCurrentMonth(), year = getCurrentYear()),
            accentColor = Color(0xff0060f0)
        ),
        Job(
            title = "Android Engineer",
            company = "Muzz",
            location = "London, UK",
            description = "",
            started = Date(month = 10, year = 2019),
            ended = Date(month = 3, year = 2023),
            accentColor = Color(0xfffb406c)
        ),
    )
}