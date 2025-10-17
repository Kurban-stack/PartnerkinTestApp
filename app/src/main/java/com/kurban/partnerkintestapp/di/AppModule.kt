package com.kurban.partnerkintestapp.di

import com.kurban.partnerkintestapp.common.data.ConferenceRepositoryImpl
import com.kurban.partnerkintestapp.common.domain.ConferenceMapper
import com.kurban.partnerkintestapp.common.domain.ConferenceRepository
import com.kurban.partnerkintestapp.features.conference.domain.GetConferenceUseCase
import com.kurban.partnerkintestapp.features.conference.ui.ConferenceDetailsViewModel
import com.kurban.partnerkintestapp.features.conferences.domain.GetConferencesUseCase
import com.kurban.partnerkintestapp.features.conferences.ui.ConferencesViewModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single {
        HttpClient(CIO) {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true; prettyPrint = true; isLenient = true })
            }
        }
    }

    single { ConferenceMapper() }
    single<ConferenceRepository> { ConferenceRepositoryImpl(get(), get()) }

    single { GetConferenceUseCase(get()) }
    single { GetConferencesUseCase(get()) }

    viewModel { ConferencesViewModel(get()) }
    viewModel { ConferenceDetailsViewModel(get()) }
}

