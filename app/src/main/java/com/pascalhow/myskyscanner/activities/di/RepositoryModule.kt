package com.pascalhow.myskyscanner.activities.di

import com.pascalhow.myskyscanner.activities.flights.CloudFlightDataStore
import com.pascalhow.myskyscanner.activities.flights.FlightDataStore
import com.pascalhow.myskyscanner.activities.flights.Repository
import com.pascalhow.myskyscanner.activities.flights.FlightRepository
import com.pascalhow.myskyscanner.rest.FlightResultsDataMapper
import com.pascalhow.myskyscanner.rest.RestClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideFlightResultsDataMapper() = FlightResultsDataMapper()

    @Provides
    @Singleton
    fun provideCloudDataStore(restClient: RestClient, dataMapper: FlightResultsDataMapper): FlightDataStore = CloudFlightDataStore(restClient, dataMapper)

    @Provides
    @Singleton
    fun provideTripsRepository(flightDataStore: FlightDataStore): Repository = FlightRepository(flightDataStore)
}
