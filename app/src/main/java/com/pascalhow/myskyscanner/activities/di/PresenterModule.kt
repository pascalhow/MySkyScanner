package com.pascalhow.myskyscanner.activities.di

import com.pascalhow.myskyscanner.activities.flights.FlightDetailsPresenter
import com.pascalhow.myskyscanner.activities.flights.Interactor
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule
{
    @Provides
    @Singleton
    fun provideFlightDetailsPresenter(
        interactor: Interactor,
        schedulersProvider: SchedulersProvider
    ): FlightDetailsPresenter = FlightDetailsPresenter(interactor, schedulersProvider)
}
