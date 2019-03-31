package com.pascalhow.myskyscanner.activities.di

import com.pascalhow.myskyscanner.activities.flights.Repository
import com.pascalhow.myskyscanner.activities.flights.TripsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideTripsRepository(): Repository = TripsRepository()
}
