package com.example.soundquest2.ui.screen.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.ui.component.bg.GradientBackground
import com.example.soundquest2.ui.component.button.MainButton
import com.example.soundquest2.ui.component.card.MainCard
import com.example.soundquest2.ui.component.icon.PressableIcon
import com.example.soundquest2.ui.component.title.LargeTitle
import com.example.soundquest2.ui.theme.common.LocalCustomThemeProperties

@Composable
fun MainScreen(
    onDecadeClick: () -> Unit,
    onGenreClick: () -> Unit,
    onFastStartClick: () -> Unit,
    onStatClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onMarketClick: () -> Unit,
) {
    GradientBackground(
        modifier = Modifier.fillMaxSize(),
        style = LocalCustomThemeProperties.current.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                MainCard(
                    modifier = Modifier.fillMaxWidth(),
                    mainCardStyle = LocalCustomThemeProperties.current.card
                ) {
                    Column(
                        modifier = Modifier
                            .padding(28.dp)
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        LargeTitle(
                            text = "SoundQuest",
                            textColor = LocalCustomThemeProperties.current.textStyle.titleLarge.color
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            MainButton(
                                label = stringResource(R.string.menu_eras),
                                buttonStyle = LocalCustomThemeProperties.current.buttons[0],
                                onClick = onDecadeClick,
                                textStyle = LocalCustomThemeProperties.current.textStyle
                            )
                            MainButton(
                                label = stringResource(R.string.menu_genres),
                                buttonStyle = LocalCustomThemeProperties.current.buttons[1],
                                onClick = onGenreClick,
                                textStyle = LocalCustomThemeProperties.current.textStyle
                            )
                            MainButton(
                                label = stringResource(R.string.menu_fast_start),
                                buttonStyle = LocalCustomThemeProperties.current.buttons[2],
                                onClick = onFastStartClick,
                                textStyle = LocalCustomThemeProperties.current.textStyle
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 15.dp, vertical = 5.dp),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            PressableIcon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Settings",
                                onClick = onSettingsClick
                            )

                            PressableIcon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "Market",
                                onClick = onMarketClick
                            )

                            PressableIcon(
                                imageVector = Icons.Default.BarChart,
                                contentDescription = "Statistics",
                                onClick = onStatClick
                            )
                        }
                    }
                }
            }
        }
    }
}

