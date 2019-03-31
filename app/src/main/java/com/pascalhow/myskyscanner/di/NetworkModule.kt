package com.pascalhow.myskyscanner.di

import com.pascalhow.myskyscanner.rest.FlightSearchRestClient
import com.pascalhow.myskyscanner.rest.RestClient
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRestClient(): RestClient = FlightSearchRestClient
}
