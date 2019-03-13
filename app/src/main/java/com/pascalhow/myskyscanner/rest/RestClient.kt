package com.pascalhow.myskyscanner.rest

import com.pascalhow.myskyscanner.activities.search.FlightsSearch
import com.pascalhow.myskyscanner.rest.model.FlightsResult
import io.reactivex.Observable

object RestClient {

    private const val SESSION_CREATION_HEADER_LOCATION = "Location"

    private val skyScannerApiService by lazy {
        SkyScannerApiService.create()
    }

    fun beginSearch(map: MutableMap<String, String>): Observable<FlightsResult> {
        val flightsSearch = FlightsSearch()

        return skyScannerApiService.createSession(map)
            .flatMap { response ->
            val sessionUrl = response.headers().get(SESSION_CREATION_HEADER_LOCATION)
            skyScannerApiService.request(sessionUrl!!, flightsSearch.apiKey, pageIndex = "0", pageSize = "10")
        }
    }

}
