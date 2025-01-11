package com.gromo.masterdetailshowcase.features.home.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
) {
    /*val countries = observeAllCountriesUseCase().collectAsStateWithLifecycle(
        emptyList()
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(countries.value) { country ->
            Text(
                text = country.nameCommon,
                modifier = Modifier.padding(16.dp)
            )
        }
    }*/
}
