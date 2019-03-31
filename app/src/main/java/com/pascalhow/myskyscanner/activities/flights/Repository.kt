package com.pascalhow.myskyscanner.activities.flights

import io.reactivex.Observable

interface Repository {

    //  We should not care where the data comes from. Can be from the cloud, DB or cache
    fun getTrips(params: MutableMap<String, String>): Observable<List<TripDataModel>>
}
