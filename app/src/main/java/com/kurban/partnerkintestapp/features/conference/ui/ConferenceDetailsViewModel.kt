package com.kurban.partnerkintestapp.features.conference.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kurban.partnerkintestapp.features.conference.domain.GetConferenceUseCase
import com.kurban.partnerkintestapp.features.conference.ui.state.ConferenceState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ConferenceDetailsViewModel(private val getConference: GetConferenceUseCase) : ViewModel() {
    private val mutableState = MutableStateFlow<ConferenceState>(ConferenceState.Loading)
    val state: StateFlow<ConferenceState> = mutableState

    init {
        fetchConference()
    }

    private fun fetchConference() {
        viewModelScope.launch {
            val result = getConference(Unit)
            if (result != null) {
                mutableState.value = ConferenceState.Content(result)
            } else {
                mutableState.value = ConferenceState.Error
            }
        }
    }
}