package com.pascalhow.myskyscanner.activities.di

import com.pascalhow.myskyscanner.activities.flights.TripModelDataMapperImpl
import com.pascalhow.myskyscanner.mapper.TripModelDataMapper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataMapperModule {

    @Provides
    @Singleton
    fun provideTripModelDataMapper(): TripModelDataMapper = TripModelDataMapperImpl()
}
