package com.kurban.partnerkintestapp.common.data.response

import kotlinx.serialization.Serializable

@Serializable
data class GetConferenceResponse(
    val error: String?,
    val data: ConferenceResponse,
)
