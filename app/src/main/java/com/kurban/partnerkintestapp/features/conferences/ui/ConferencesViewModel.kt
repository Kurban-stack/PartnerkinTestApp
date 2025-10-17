package com.kurban.partnerkintestapp.features.conferences.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurban.partnerkintestapp.common.domain.model.Conference
import com.kurban.partnerkintestapp.features.conferences.domain.GetConferencesUseCase
import com.kurban.partnerkintestapp.features.conferences.ui.state.ConferencesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class ConferencesViewModel(
    private val getConferencesUseCase: GetConferencesUseCase
) : ViewModel() {
    private val mutableState = MutableStateFlow<ConferencesState>(ConferencesState.Loading)
    val state: StateFlow<ConferencesState> = mutableState

    init {
        fetchConferences()
    }

    private fun fetchConferences() {
        viewModelScope.launch {
            val result = getConferencesUseCase(Unit)
            if (result != null) {
                mutableState.value = ConferencesState.Content(groupConferencesByMonth(result))
            } else {
                mutableState.value = ConferencesState.Error
            }
        }
    }
}

private fun groupConferencesByMonth(conferences: List<Conference>): Map<String, List<Conference>> {
    val formatter = SimpleDateFormat("MMMM, yyyy", Locale("ru"))
    return conferences
        .sortedBy { it.startDate }
        .groupBy { formatter.format(it.startDate) }
}
