package com.example.soundquest2.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.domain.model.enums.Genre
import com.example.soundquest2.ui.component.bg.GradientBackground
import com.example.soundquest2.ui.component.button.MainButton
import com.example.soundquest2.ui.component.card.MainCard
import com.example.soundquest2.ui.theme.common.LocalCustomThemeProperties
import com.example.soundquest2.ui.util.localizedName

@Composable
fun GenreSelectionScreen(onGenreSelected: (Genre) -> Unit) {

    val genres = Genre.entries.toTypedArray()

    GradientBackground(modifier = Modifier.fillMaxSize(), LocalCustomThemeProperties.current.background) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp), contentAlignment = Alignment.Center
        ) {
            MainCard(modifier = Modifier.fillMaxWidth(), LocalCustomThemeProperties.current.card) {
                Column(
                    modifier = Modifier
                        .padding(28.dp)
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.genres_title),
                        style = LocalCustomThemeProperties.current.textStyle.titleMedium,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                    Spacer(Modifier.height(16.dp))
                    genres.forEachIndexed { index, genre ->
                        MainButton(
                            label = genre.localizedName(),
                            onClick = { onGenreSelected(genre) },
                            buttonStyle = LocalCustomThemeProperties.current.buttons[index],
                            textStyle = LocalCustomThemeProperties.current.textStyle
                        )
                    }
                }
            }
        }
    }
}