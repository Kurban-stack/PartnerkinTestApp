package com.kurban.partnerkintestapp.common.domain.model

import java.util.Date

data class Conference(
    val id: Int,
    val name: String,
    val status: Status,
    val statusTitle: String,
    val imageUrl: String,
    val startDate: Date, // формат "yyyy-MM-dd"
    val endDate: Date,
    val oneDay: Int,
    val country: String,
    val city: String,
    val categories: List<String>,
    val about: String?,
)

enum class Status(val status: String) {
    PUBLISH("publish"),
    CANCELED("canceled")
}
