package com.pascalhow.myskyscanner.activities.flights

import io.reactivex.Observable

interface Repository {

    //  TODO: Consider getting from local and remote data source
    fun getTrips(params: MutableMap<String, String>): Observable<List<TripDataModel>>
}
