package com.kurban.partnerkintestapp.features.conference.domain

import com.kurban.partnerkintestapp.common.domain.ConferenceRepository
import com.kurban.partnerkintestapp.common.domain.model.Conference
import com.kurban.partnerkintestapp.core.domain.CoroutineUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class GetConferenceUseCase(
    private val repository: ConferenceRepository,
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : CoroutineUseCase<Unit, Conference>(coroutineDispatcher = dispatcher) {

    override suspend fun execute(parameters: Unit): Conference {
        return repository.getConference()
    }
}