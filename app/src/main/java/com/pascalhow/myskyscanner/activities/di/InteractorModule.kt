package com.pascalhow.myskyscanner.activities.di

import com.pascalhow.myskyscanner.activities.flights.FlightDetailsInteractor
import com.pascalhow.myskyscanner.activities.flights.Interactor
import com.pascalhow.myskyscanner.rest.FlightSearchRestClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideFlightDetailsInteractor(restClient: FlightSearchRestClient): Interactor = FlightDetailsInteractor(restClient)
}
