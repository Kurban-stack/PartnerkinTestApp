package com.kurban.partnerkintestapp

import android.app.Application
import com.kurban.partnerkintestapp.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class PartnerkinApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@PartnerkinApp)
            modules(appModule)
        }
    }
}