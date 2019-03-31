package com.pascalhow.myskyscanner.activities.di

import com.pascalhow.myskyscanner.activities.flights.TripViewModelDataMapperImpl
import com.pascalhow.myskyscanner.mapper.TripViewModelDataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataMapperModule {

    @Provides
    @Singleton
    fun provideTripViewModelDataMapper(): TripViewModelDataMapper = TripViewModelDataMapperImpl()
}
