package com.example.soundquest2.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.domain.model.GameMode
import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.domain.model.enums.Genre
import com.example.soundquest2.ui.component.ActionButton
import com.example.soundquest2.ui.component.DropdownSelector
import com.example.soundquest2.ui.component.MainBackground
import com.example.soundquest2.ui.intent.GameIntent
import com.example.soundquest2.ui.theme.AppTypography
import com.example.soundquest2.ui.theme.LocalAppImages
import com.example.soundquest2.ui.util.localizedName

@Composable
fun SongSelectionScreen(
    onExit: () -> Unit,
    onIntent: (GameIntent) -> Unit,
    toDownloadScreen: () -> Unit
) {

    var selectedEra by remember { mutableStateOf<Era?>(null) }
    var selectedGenre by remember { mutableStateOf<Genre?>(null) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        MainBackground(LocalAppImages.current.bg)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {

            ActionButton(
                modifier = Modifier.align(Alignment.TopStart).padding(16.dp),
                text = stringResource(R.string.back),
                onAction = {
                    onExit()
                }
            )

            Column(
                modifier = Modifier.fillMaxWidth().offset(y = -60.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.select_parameters),
                    style = AppTypography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                DropdownSelector(
                    items = Era.entries,
                    selectedItem = selectedEra,
                    onItemSelected = { era -> selectedEra = era },
                    itemLocalization = { it.localizedName() },
                    placeholder = stringResource(R.string.select_decade)
                )

                DropdownSelector(
                    items = Genre.entries,
                    selectedItem = selectedGenre,
                    onItemSelected = { genre -> selectedGenre = genre },
                    itemLocalization = { it.localizedName() },
                    placeholder = stringResource(R.string.select_genre)
                )

                ActionButton(
                    text = stringResource(R.string.start),
                    onAction = {
                        onIntent(
                            GameIntent.SetMode(
                                GameMode.GuessSong(
                                    era = selectedEra,
                                    genre = selectedGenre
                                )
                            )
                        )
                        toDownloadScreen()
                    }
                )
            }
        }
    }
}