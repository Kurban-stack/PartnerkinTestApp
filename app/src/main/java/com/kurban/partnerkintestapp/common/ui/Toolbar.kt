package com.kurban.partnerkintestapp.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kurban.partnerkintestapp.ui.theme.AppTypography
import com.kurban.partnerkintestapp.ui.theme.PartnerkinTheme
import com.kurban.partnerkintestapp.ui.theme.spacing

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String? = null,
    leftIcon: @Composable () -> Unit = {},
    rightIcon: @Composable () -> Unit = {}
) {
    Row(
        modifier = modifier
            .height(48.dp)
            .padding(horizontal = MaterialTheme.spacing.large),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            leftIcon()
        }

        Box(
            modifier = Modifier.weight(3f),
            contentAlignment = Alignment.Center
        ) {
            title?.let {
                Text(
                    text = it,
                    style = AppTypography.titleSmall,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        Box(
            modifier = Modifier.weight(1f),
            contentAlignment = Alignment.CenterEnd
        ) {
            rightIcon()
        }
    }
}

@Preview
@Composable
private fun ToolbarPreview() {
    PartnerkinTheme {
        Toolbar(
            title = "Some text"
        )
    }
}