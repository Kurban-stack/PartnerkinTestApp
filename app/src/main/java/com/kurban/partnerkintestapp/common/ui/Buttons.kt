package com.kurban.partnerkintestapp.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kurban.partnerkintestapp.ui.theme.AppTypography

@Composable
fun PrimaryButton(
    modifier: Modifier,
    title: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .clickable { onClick() }
            .background(
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.large
            )
    ) {

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            style = AppTypography.headlineMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
    }
}