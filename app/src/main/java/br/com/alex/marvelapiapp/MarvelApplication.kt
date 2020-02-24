package br.com.alex.marvelapiapp

import android.app.Application
import android.content.Context

class MarvelApplication : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}