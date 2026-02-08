package com.example.soundquest2.ui.component

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.soundquest2.domain.model.enums.Era
import com.example.soundquest2.ui.theme.AppTheme
import com.example.soundquest2.ui.theme.AppTypography
import com.example.soundquest2.ui.util.localizedName

@Composable
fun <T> DropdownSelector(
    items: List<T>,
    selectedItem: T?,
    onItemSelected: (T) -> Unit,
    itemLocalization: @Composable (T) -> String,
    placeholder: String,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }

    val arrowRotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        label = "ArrowRotation"
    )

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .width(332.dp)
                .height(75.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable { expanded = true }
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = selectedItem?.let { itemLocalization(it) } ?: placeholder,
                modifier = Modifier.weight(1f),
                style = AppTypography.labelSmall,
                color = if (selectedItem == null)
                    MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.4f)
                else
                    MaterialTheme.colorScheme.onPrimary
            )

            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = null,
                modifier = Modifier.rotate(arrowRotation),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(332.dp)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = RoundedCornerShape(10.dp)
                )
        ) {

            items.forEach { item ->
                val isSelected = item == selectedItem

                DropdownMenuItem(
                    text = {
                        Text(
                            text = itemLocalization(item),
                            style = AppTypography.labelSmall,
                            color = if (isSelected)
                                MaterialTheme.colorScheme.secondary
                            else
                                MaterialTheme.colorScheme.onPrimary
                        )
                    },
                    onClick = {
                        onItemSelected(item)
                        expanded = false
                    },
                    modifier = Modifier
                        .background(
                            if (isSelected)
                                MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f)
                            else
                                Color.Transparent
                        )
                )
            }
        }
    }
}

@Preview(
    name = "Era Dropdown Selector",
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun EraDropdownSelectorPreview() {
    AppTheme(false) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            contentAlignment = Alignment.Center
        ) {
            var selectedEra by remember { mutableStateOf<Era?>(null) }
            DropdownSelector(
                items = Era.entries,
                selectedItem = selectedEra,
                onItemSelected = {era -> selectedEra = era},
                itemLocalization = { it.localizedName() },
                placeholder = "Era",
            )
        }
    }
}

