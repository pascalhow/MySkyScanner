package com.pascalhow.myskyscanner.application

import android.app.Application
import com.pascalhow.myskyscanner.di.AppComponent
import com.pascalhow.myskyscanner.di.AppModule
import com.pascalhow.myskyscanner.di.DaggerAppComponent

class MySkyScannerApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
    }

    private fun initDagger(app: MySkyScannerApplication): AppComponent =
        DaggerAppComponent.builder()
            .appModule(AppModule(app))
            .build()
}
