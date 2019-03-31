package com.pascalhow.myskyscanner.activities.di

import com.pascalhow.myskyscanner.activities.flights.FlightDetailsInteractor
import com.pascalhow.myskyscanner.activities.flights.Repository
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideFlightDetailsInteractor(
        repository: Repository,
        schedulersProvider: SchedulersProvider
    ): FlightDetailsInteractor {
        return FlightDetailsInteractor(repository, schedulersProvider)
    }
}
