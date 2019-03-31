package com.pascalhow.myskyscanner.activities.di

import com.pascalhow.myskyscanner.activities.flights.FlightDetailsInteractor
import com.pascalhow.myskyscanner.activities.flights.FlightDetailsPresenter
import com.pascalhow.myskyscanner.mapper.TripModelDataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PresenterModule
{
    @Provides
    @Singleton
    fun provideFlightDetailsPresenter(
        dataMapper: TripModelDataMapper,
        interactor: FlightDetailsInteractor
    ): FlightDetailsPresenter = FlightDetailsPresenter(dataMapper, interactor)
}
