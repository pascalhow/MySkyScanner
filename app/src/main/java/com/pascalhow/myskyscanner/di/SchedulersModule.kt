package com.pascalhow.myskyscanner.di

import com.pascalhow.myskyscanner.utils.FlightsSchedulersProvider
import com.pascalhow.myskyscanner.utils.SchedulersProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulersModule {

    @Provides
    @Singleton
    fun provideSchedulersProvider(): SchedulersProvider = FlightsSchedulersProvider()
}

