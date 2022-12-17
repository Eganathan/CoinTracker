package online.onenut.cointracker

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PreviewTagChip(
    text: String,
    enabled: Boolean = false,
    onClick: () -> Unit = {},
    backgroundColor: Color = Color.Red.copy(alpha = 0.1f),
    contentColor: Color = Color.Black.copy(alpha = 0.7f)
) {
    Chip(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .padding(0.dp)
            .padding(end = 5.dp)
            .defaultMinSize(minHeight = 20.dp),
        colors = ChipDefaults.chipColors(disabledBackgroundColor = backgroundColor)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall.copy(color = contentColor),
            modifier = Modifier.padding(0.dp)
        )
    }
}