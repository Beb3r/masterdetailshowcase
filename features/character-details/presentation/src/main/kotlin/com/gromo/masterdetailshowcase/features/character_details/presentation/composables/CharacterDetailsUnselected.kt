package com.gromo.masterdetailshowcase.features.character_details.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.gromo.masterdetailshowcase.libraries.translations.R.string as translations


@Composable
fun CharacterDetailsUnselected() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Text(
            color = contentColorFor(MaterialTheme.colorScheme.background),
            text = stringResource(translations.character_details_unselected),
        )
    }
}