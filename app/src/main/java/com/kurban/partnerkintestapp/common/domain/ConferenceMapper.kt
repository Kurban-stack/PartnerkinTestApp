package com.kurban.partnerkintestapp.common.domain

import com.kurban.partnerkintestapp.common.data.response.ConferenceResponse
import com.kurban.partnerkintestapp.common.domain.model.Conference
import com.kurban.partnerkintestapp.common.domain.model.Status
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val DATE_FORMAT = "yyyy-MM-dd"
class ConferenceMapper() {

    private val format = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    fun map(response: ConferenceResponse): Conference =
        Conference(
            id = response.id,
            name = response.name,
            status = Status.entries.find { it.name.equals(response.status, ignoreCase = true) } ?: Status.PUBLISH,
            statusTitle = response.statusTitle,
            imageUrl = response.image.url,
            startDate = format.parse(response.startDate) ?: Date(),
            endDate = format.parse(response.endDate) ?: Date(),
            oneDay = response.oneDay,
            country = response.country,
            city = response.city,
            categories = response.categories.map { it.name },
            about = response.about
        )
}