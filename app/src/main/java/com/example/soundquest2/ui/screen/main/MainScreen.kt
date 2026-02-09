package com.example.soundquest2.ui.screen.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.ui.component.MainBackground
import com.example.soundquest2.ui.component.MainIcon
import com.example.soundquest2.ui.component.MainButton
import com.example.soundquest2.ui.intent.MainIntent
import com.example.soundquest2.ui.theme.AppTheme
import com.example.soundquest2.ui.theme.AppTypography
import com.example.soundquest2.ui.theme.LocalAppImages

@Composable
fun MainScreen(
    onIntent: (MainIntent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {

        MainBackground(LocalAppImages.current.bg)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    text = stringResource(R.string.app_name),
                    style = AppTypography.titleLarge,
                    color = Color.White
                )

                MainButton(stringResource(R.string.soundtracks)) {
                    onIntent(MainIntent.SoundtracksClicked)
                }

                MainButton(stringResource(R.string.songs)) {
                    onIntent(MainIntent.SongsClicked)
                }

                MainButton(stringResource(R.string.game_mode_fast_start)) {
                    onIntent(MainIntent.FastStartClicked)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 55.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    MainIcon(R.drawable.achivments_icon) {
                        onIntent(MainIntent.AchievementsClicked)
                    }

                    MainIcon(R.drawable.market_icon) {
                        onIntent(MainIntent.MarketClicked)
                    }

                    MainIcon(R.drawable.settings_icon) {
                        onIntent(MainIntent.SettingsClicked)
                    }
                }
            }
        }
    }
}

@Preview(
    name = "Light",
    showBackground = true,
    locale = "ru"
)
@Composable
fun HomeScreenLightPreviewLight() {
    AppTheme(darkTheme = false) {
        MainScreen(
            {},
        )
    }
}
@Preview(
    name = "Dark",
    showBackground = true,
    locale = "en"
)
@Composable
fun HomeScreenLightPreviewDark() {
    AppTheme(darkTheme = true) {
        MainScreen(
            {},
        )
    }
}