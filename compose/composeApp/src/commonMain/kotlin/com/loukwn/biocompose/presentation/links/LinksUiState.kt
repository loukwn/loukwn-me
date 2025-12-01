package com.loukwn.biocompose.presentation.links

import com.loukwn.biocompose.data.FullLink

data class LinksUiState(
    val groupedLinks: Map<Char, List<FullLink>>,
    val selectedLink: FullLink?,
)
