package com.loukwn.biocompose.presentation.aboutme

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun AboutMeBg(modifier: Modifier = Modifier) {
    val clouds = remember { buildClouds() }
    Canvas(modifier = modifier) {
        clouds.forEach { cloud ->
            val path = Path().apply {
                val firstPoint = cloud.first()
                moveTo(firstPoint.x1, firstPoint.y1)

                for (i in cloud.indices) {
                    val cloudPoint = cloud[i]

                    cubicTo(
                        x1 = cloudPoint.x1,
                        y1 = cloudPoint.y1,
                        x2 = cloudPoint.x2,
                        y2 = cloudPoint.y2,
                        x3 = cloudPoint.x3,
                        y3 = cloudPoint.y3,
                    )
                }
            }

            drawPath(
                path = path,
                style = Stroke(),
                color = Color.White.copy(alpha = .1f),
            )
        }
    }
}

private data class CloudCubicArc(
    val x1: Float,
    val y1: Float,
    val x2: Float,
    val y2: Float,
    val x3: Float,
    val y3: Float,
)

private fun buildClouds(): List<List<CloudCubicArc>> {
    return buildList {
        repeat(20) {
            val itemNumber = it + 1
            add(
                buildCloud(
                    radius = 50 * itemNumber,
                    numOfPoints = 6 + 2 * it,
                    variance = 20 + itemNumber
                ),
            )
        }
    }
}

private fun buildCloud(radius: Int, numOfPoints: Int, variance: Int): List<CloudCubicArc> {
    val centerPoint = Offset(200f, 200f)

    return buildList {
        repeat(numOfPoints) {
            val index = it + 1

            val angleFirst = index * 360.0 / numOfPoints
            val angleNext = ((index + 1)) * 360.0 / numOfPoints

            val x1 = centerPoint.x + radius * cos(angleFirst * PI / 180)
            val y1 = centerPoint.y + radius * sin(angleFirst * PI / 180)
            val x3 = centerPoint.x + radius * cos(angleNext * PI / 180)
            val y3 = centerPoint.y + radius * sin(angleNext * PI / 180)

            val midPointX = (x1 + x3) / 2
            val midPointY = (y1 + y3) / 2
            val goInwards = it % 2 == 1
            val slopeOffset = Random.nextInt(3 * variance / 4, variance)

            val (x2, y2) = if (goInwards) {
                when {
                    // topRight
                    x3 > x1 && y3 > y1 -> midPointX - slopeOffset to midPointY + slopeOffset
                    // bottomRight
                    x3 < x1 && y3 > y1 -> midPointX - slopeOffset to midPointY - slopeOffset
                    // bottomLeft
                    x3 < x1 && y3 < y1 -> midPointX + slopeOffset to midPointY - slopeOffset
                    // topLeft
                    x3 > x1 && y3 < y1 -> midPointX + slopeOffset to midPointY + slopeOffset
                    else -> midPointX to midPointY
                }
            } else {
                when {
                    // topRight
                    x3 > x1 && y3 > y1 -> midPointX + slopeOffset to midPointY - slopeOffset
                    // bottomRight
                    x3 < x1 && y3 > y1 -> midPointX + slopeOffset to midPointY + slopeOffset
                    // bottomLeft
                    x3 < x1 && y3 < y1 -> midPointX - slopeOffset to midPointY + slopeOffset
                    // topLeft
                    x3 > x1 && y3 < y1 -> midPointX - slopeOffset to midPointY - slopeOffset
                    else -> midPointX to midPointY
                }
            }


            add(
                CloudCubicArc(
                    x1.toFloat(),
                    y1.toFloat(),
                    x2.toFloat(),
                    y2.toFloat(),
                    x3.toFloat(),
                    y3.toFloat()
                )
            )
        }
    }
}
