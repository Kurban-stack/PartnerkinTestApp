package com.kurban.partnerkintestapp.common.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit,
    backgroundColor: Color = Color(0xFFFFFFFF),
) {
    Box(
        modifier = modifier
            .wrapContentSize()
            .background(color = backgroundColor, shape = RoundedCornerShape(50)),
    ) {
        title()
    }
}