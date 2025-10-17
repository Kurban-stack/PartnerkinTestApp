package com.kurban.partnerkintestapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Composition
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun PartnerkinTheme(content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colorScheme = lightColorScheme(
                primary = AppColors.Primary,
                secondary = AppColors.Secondary,
                background = AppColors.Background,
                surface = AppColors.Surface,
                tertiary = AppColors.Tertiery,
                error = AppColors.Error,
                onPrimary = AppColors.OnPrimary,
                onPrimaryContainer = AppColors.OnPrimaryContainer,
                onSecondary = AppColors.OnSecondary,
                onBackground = AppColors.OnBackground,
                onSurface = AppColors.OnSurface,
                onErrorContainer = AppColors.OnErrorContainer,
                outline = AppColors.Outline,
                outlineVariant = AppColors.OutlineVariant,
                onError = AppColors.OnError
            ),
            typography = AppTypography,
            shapes = AppShapes,
            content = content
        )
    }
}