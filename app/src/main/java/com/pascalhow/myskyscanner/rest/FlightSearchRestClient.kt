package com.pascalhow.myskyscanner.rest

import com.pascalhow.myskyscanner.rest.model.FlightsResult
import io.reactivex.Observable

object FlightSearchRestClient : RestClient {

    private const val SESSION_CREATION_HEADER_LOCATION = "Location"

    private val skyScannerApiService by lazy {
        SkyScannerApiService.create()
    }

    override fun getSessionUrl(map: MutableMap<String, String>): Observable<String> {
        return skyScannerApiService.createSession(map)
            .map { response ->
                response.headers().get(SESSION_CREATION_HEADER_LOCATION)
            }
    }

    override fun search(
        sessionUrl: String,
        apiKey: String,
        pageIndex: String,
        pageSize: String
    ): Observable<FlightsResult> {
        return skyScannerApiService.request(sessionUrl, apiKey, pageIndex = pageIndex, pageSize = pageSize)
    }

}
