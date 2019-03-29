package com.pascalhow.myskyscanner.di

import com.pascalhow.myskyscanner.activities.di.InteractorModule
import com.pascalhow.myskyscanner.activities.di.PresenterModule
import com.pascalhow.myskyscanner.activities.flights.FlightSearchActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        PresenterModule::class,
        SchedulersModule::class,
        NetworkModule::class,
        InteractorModule::class
    ]
)
interface AppComponent {

    fun inject(target: FlightSearchActivity)
}
