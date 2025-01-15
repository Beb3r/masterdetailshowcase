package com.gromo.masterdetailshowcase.core.design

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun AppImage(
    modifier: Modifier = Modifier,
    contentDescription: String,
    url: String,
    alignment: Alignment = Alignment.Center,
    contentScale: ContentScale = ContentScale.Fit,
) {
    AsyncImage(
        modifier = modifier,
        model = url,
        contentDescription = contentDescription,
        alignment = alignment,
        contentScale = contentScale,
    )
}
