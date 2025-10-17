package com.kurban.partnerkintestapp.features.conferences.domain

import com.kurban.partnerkintestapp.common.domain.ConferenceRepository
import com.kurban.partnerkintestapp.common.domain.model.Conference
import com.kurban.partnerkintestapp.core.domain.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetConferencesUseCase(
    private val repository: ConferenceRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : CoroutineUseCase<Unit, List<Conference>>(coroutineDispatcher = dispatcher) {

    override suspend fun execute(parameters: Unit): List<Conference> {
        return repository.getConferences()
    }
}
