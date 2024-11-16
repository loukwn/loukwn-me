package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.loukwn.biocompose.data.Project
import com.loukwn.biocompose.presentation.design_system.components.VectorIconButton

@Composable
fun ProjectsPage(projects: List<Project>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier, verticalArrangement = spacedBy(20.dp)) {
        items(projects.size) {
            ProjectCard(projects[it])
        }
    }
}

@Composable
private fun ProjectCard(model: Project) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(portfolioAccentColor.copy(alpha = .05f), shape = RoundedCornerShape(24.dp)),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = model.title, color = Color.White, fontSize = 18.sp)

            Row(horizontalArrangement = spacedBy(16.dp)) {
                model.technologies.forEach {
                    Icon(
                        imageVector = it,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        }

        Divider(color = Color.White.copy(alpha = .3f))

        Text(
            text = model.description,
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontSize = 14.sp
        )

        val uriHandler = LocalUriHandler.current
        Row(
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp, bottom = 16.dp),
            horizontalArrangement = spacedBy(16.dp)
        ) {
            model.links.forEach { link ->
                VectorIconButton(imageVector = link.icon) {
                    uriHandler.openUri(link.url)
                }
            }
        }

    }
}