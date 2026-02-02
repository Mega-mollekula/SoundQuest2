package com.example.soundquest2.ui.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.soundquest2.R
import com.example.soundquest2.domain.model.GameResult
import com.example.soundquest2.ui.component.item.ResultItem
import com.example.soundquest2.ui.theme.style.ResultCardStyle
import com.example.soundquest2.ui.util.formatTimestamp
import com.example.soundquest2.ui.util.localizedName

@Composable
fun ResultCard(
    gameResult: GameResult,
    cardStyle: ResultCardStyle,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = cardStyle.cardColor,
                shape = RoundedCornerShape(22.dp)
            )
            .padding(9.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        ResultItem(
            label = stringResource(R.string.score),
            value = "${gameResult.guessedSongsCount} / ${gameResult.roundsCount}"
        )
        ResultItem(
            label = stringResource(R.string.game_mode),
            value = gameResult.gameMode.localizedName(),
        )
        ResultItem(
            label = stringResource(R.string.finished_at),
            value = formatTimestamp(gameResult.createdAt)
        )
    }
}