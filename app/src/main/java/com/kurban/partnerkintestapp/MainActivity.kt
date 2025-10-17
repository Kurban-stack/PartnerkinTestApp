package com.kurban.partnerkintestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kurban.partnerkintestapp.features.conference.ui.ConferenceScreen
import com.kurban.partnerkintestapp.features.conferences.ui.ConferencesScreen
import com.kurban.partnerkintestapp.ui.theme.PartnerkinTheme

private const val CONFERENCES_ROOT = "conferences"
private const val CONFERENCE_ROOT = "conference"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PartnerkinTheme {
                var currentScreen by remember { mutableStateOf(CONFERENCES_ROOT) }

                when (currentScreen) {
                    CONFERENCES_ROOT -> ConferencesScreen(onNavigateToSecond = { currentScreen = CONFERENCE_ROOT })
                    CONFERENCE_ROOT -> ConferenceScreen(onNavigateBack = { currentScreen = CONFERENCES_ROOT })
                }
            }
        }
    }
}