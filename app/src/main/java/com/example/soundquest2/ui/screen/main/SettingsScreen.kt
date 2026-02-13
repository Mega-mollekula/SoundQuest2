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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.ui.component.ActionButton
import com.example.soundquest2.ui.component.MainBackground
import com.example.soundquest2.ui.component.MainButton
import com.example.soundquest2.ui.theme.AppTypography
import com.example.soundquest2.ui.theme.LocalAppImages

@Composable
fun SettingsScreen(
    toLanguageScreen: () -> Unit,
    toThemeScreen: () -> Unit,
    onExit: () -> Unit,
) {

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
                    text = stringResource(R.string.settings),
                    style = AppTypography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )

                MainButton(
                    text = stringResource(R.string.theme),
                    onClick = {
                        toThemeScreen()
                    }
                )

                MainButton(
                    text = stringResource(R.string.language),
                    onClick = {
                        toLanguageScreen()
                    }
                )
            }
        }
    }
}