package com.kurban.partnerkintestapp.features.conference.ui.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.kurban.partnerkintestapp.common.domain.model.Conference

@Stable
sealed interface ConferenceState {
    @Immutable
    object Loading: ConferenceState
    @Immutable
    data class Content(val conferense: Conference): ConferenceState
    @Immutable
    object Error: ConferenceState
}
