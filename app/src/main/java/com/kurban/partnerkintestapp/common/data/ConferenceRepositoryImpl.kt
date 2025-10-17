package com.kurban.partnerkintestapp.common.data

import com.kurban.partnerkintestapp.common.data.response.GetConferenceResponse
import com.kurban.partnerkintestapp.common.data.response.GetConferencesResponse
import com.kurban.partnerkintestapp.common.domain.ConferenceMapper
import com.kurban.partnerkintestapp.common.domain.ConferenceRepository
import com.kurban.partnerkintestapp.common.domain.model.Conference
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

private const val GET_CONFERENCES =
    "https://partnerkin.com/api_ios_test/list?api_key=DMwdj29q@S29shslok2"
private const val GET_CONFERENCE =
    "https://partnerkin.com/api_ios_test/view?api_key=DMwdj29q@S29shslok2"

class ConferenceRepositoryImpl(
    private val client: HttpClient,
    private val mapper: ConferenceMapper
) : ConferenceRepository {
    override suspend fun getConferences(): List<Conference> {
        return client.get(GET_CONFERENCES)
            .body<GetConferencesResponse>().data.result.map { mapper.map(it.conference) }
    }

    override suspend fun getConference(): Conference {
        return mapper.map(client.get(GET_CONFERENCE).body<GetConferenceResponse>().data)
    }
}