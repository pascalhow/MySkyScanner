package com.pascalhow.myskyscanner.rest

import android.app.Application
import timber.log.Timber

class SkyScannerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
