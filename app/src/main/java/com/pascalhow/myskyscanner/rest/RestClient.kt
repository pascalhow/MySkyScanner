package com.pascalhow.myskyscanner.rest

import io.reactivex.Observable

interface RestClient {
    fun getSessionUrl(map: MutableMap<String, String>): Observable<String>

    fun search(
        sessionUrl: String,
        apiKey: String,
        pageIndex: String = "0",
        pageSize: String = "10"
    ): Observable<FlightSearchRestClient.ItinerariesMap>
}
