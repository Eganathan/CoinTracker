package online.onenut.cointracker.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import online.onenut.cointracker.PreviewTagChip

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ScrollableChips(
    stickyHeaderText: String,
    tags: List<String>,
) {
    LazyRow(
        Modifier
            .padding(start = 1.dp, top = 7.dp)
            .heightIn(min = 22.dp, max = 26.dp),
    )
    {
        stickyHeader {
            PreviewTagChip(
                text = stickyHeaderText,
                backgroundColor = Color.LightGray,
                contentColor = Color.White
            )
        }
        items(tags) {
            PreviewTagChip(
                text = it
            )
        }
    }
}