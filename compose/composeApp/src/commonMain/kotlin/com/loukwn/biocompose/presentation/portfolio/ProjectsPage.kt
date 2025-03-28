package com.loukwn.biocompose.presentation.portfolio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.loukwn.biocompose.data.Project
import com.loukwn.biocompose.presentation.design_system.components.VectorIconButton

@Composable
fun ProjectsPage(projects: List<Project>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = spacedBy(20.dp),
        contentPadding = PaddingValues(top = 20.dp, bottom = 120.dp),
    ) {
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
            .background(MaterialTheme.colors.secondary.copy(alpha = .2f), shape = RoundedCornerShape(24.dp)),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = model.title,
                color = MaterialTheme.colors.onBackground,
                style = MaterialTheme.typography.subtitle1,
            )

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
            color = MaterialTheme.colors.onBackground,
            style = MaterialTheme.typography.body2,
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