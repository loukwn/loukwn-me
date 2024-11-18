package com.loukwn.biocompose.presentation.aboutme

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import kotlin.random.Random
import kotlin.random.nextInt

@Composable
fun AboutMeBgDrawings(modifier: Modifier = Modifier) {
    val stepSize = 80
    var maxRadius by remember { mutableStateOf(0) }
    var randomCenterOffsets by remember { mutableStateOf(emptyList<Offset>()) }
    val maxRadiusAnimated =
        animateIntAsState(maxRadius, animationSpec = tween(1500, easing = FastOutLinearInEasing))

    Canvas(
        modifier = modifier.onGloballyPositioned {
            maxRadius = it.size.height
            randomCenterOffsets = calculateRandomCenterOffsets(
                stepSize = stepSize,
                maxRadius = maxRadius,
                currentRandomCenterOffsets = randomCenterOffsets,
            )
        },
    ) {
        for (i in stepSize..maxRadiusAnimated.value step stepSize) {
            val centerIndex = i / stepSize
            if (randomCenterOffsets.isNotEmpty() && centerIndex < randomCenterOffsets.size) {
                drawCircle(
                    color = Color.White.copy(alpha = .1f),
                    radius = i.toFloat(),
                    center = randomCenterOffsets[centerIndex],
                    style = Stroke(width = 2f)
                )
            }
        }
    }
}

private fun calculateRandomCenterOffsets(
    stepSize: Int,
    maxRadius: Int,
    currentRandomCenterOffsets: List<Offset>
): List<Offset> {
    val centerOffsetsToHave = (maxRadius / stepSize.toFloat()).toInt()

    if (currentRandomCenterOffsets.isEmpty()) {
        return buildList {
            for (i in 0..centerOffsetsToHave) {
                add(
                    Offset(
                        x = Random.nextInt(0 until maxRadius).toFloat(),
                        y = Random.nextInt(0 until maxRadius).toFloat(),
                    )
                )
            }
        }
    } else {
        if (centerOffsetsToHave < currentRandomCenterOffsets.size) {
            return currentRandomCenterOffsets
                .dropLast(currentRandomCenterOffsets.size - centerOffsetsToHave)
        } else if (centerOffsetsToHave > currentRandomCenterOffsets.size) {
            val extraOffsetsToAdd = buildList {
                repeat(centerOffsetsToHave - currentRandomCenterOffsets.size) {
                    add(
                        Offset(
                            x = Random.nextInt(0 until maxRadius).toFloat(),
                            y = Random.nextInt(0 until maxRadius).toFloat(),
                        )
                    )
                }
            }
            return currentRandomCenterOffsets.plus(extraOffsetsToAdd)
        } else {
            return currentRandomCenterOffsets
        }
    }
}