package com.kurban.partnerkintestapp.common.data.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class GetConferencesResponse(
    @SerialName("error") val error: String?,
    @SerialName("data") val data: Data,
)

@Serializable
data class Data(
    @SerialName("counts") val counts: Int,
    @SerialName("pagination") val pagination: Pagination,
    @SerialName("result") val result: List<Result>
)

@Serializable
data class Pagination(
    @SerialName("page_size") val pageSize: Int,
    @SerialName("current_page") val currentPage: Int
)

@Serializable
data class Result(
    @SerialName("view_type") val viewType: Int,
    @SerialName("conference") val conference: ConferenceResponse
)

@Serializable
data class ConferenceResponse(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("format") val format: String,
    @SerialName("status") val status: String,
    @SerialName("status_title") val statusTitle: String,
    @SerialName("url") val url: String,
    @SerialName("image") val image: Image,
    @SerialName("rating") val rating: JsonElement? = null,
    @SerialName("start_date") val startDate: String,
    @SerialName("end_date") val endDate: String,
    @SerialName("oneday") val oneDay: Int,
    @SerialName("custom_date") val customDate: JsonElement? = null,
    @SerialName("country_id") val countryId: Int,
    @SerialName("country") val country: String,
    @SerialName("city_id") val cityId: Int,
    @SerialName("city") val city: String,
    @SerialName("categories") val categories: List<Category>,
    @SerialName("type_id") val typeId: Int,
    @SerialName("type") val type: Type,
    @SerialName("about") val about: String? = null
)

@Serializable
data class Image(
    @SerialName("id") val id: String,
    @SerialName("url") val url: String,
    @SerialName("preview") val preview: String,
    @SerialName("placeholder_color") val placeholderColor: JsonElement? = null,
    @SerialName("width") val width: Int,
    @SerialName("height") val height: Int
)

@Serializable
data class Category(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("url") val url: String
)

@Serializable
data class Type(
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String
)
