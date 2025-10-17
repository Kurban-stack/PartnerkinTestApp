package com.kurban.partnerkintestapp.common.domain

import com.kurban.partnerkintestapp.common.domain.model.Conference

interface ConferenceRepository {

    suspend fun getConferences(): List<Conference>

    suspend fun getConference(): Conference
}