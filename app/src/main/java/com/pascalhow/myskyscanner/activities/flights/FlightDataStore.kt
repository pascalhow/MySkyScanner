package com.pascalhow.myskyscanner.activities.flights

import com.pascalhow.myskyscanner.activities.flights.CloudFlightDataStore.ItinerariesMap
import io.reactivex.Observable

interface FlightDataStore {

    fun flightEntityList(params: MutableMap<String, String>): Observable<ItinerariesMap>
}
