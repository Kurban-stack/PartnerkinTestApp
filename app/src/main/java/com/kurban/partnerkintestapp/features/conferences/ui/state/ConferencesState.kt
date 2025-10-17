package com.kurban.partnerkintestapp.features.conferences.ui.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import com.kurban.partnerkintestapp.common.domain.model.Conference

@Stable
sealed interface ConferencesState {
    @Immutable
    object Loading: ConferencesState
    @Immutable
    data class Content(val conferenses: Map<String, List<Conference>>): ConferencesState
    @Immutable
    object Error: ConferencesState
}
