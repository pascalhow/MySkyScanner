package com.pascalhow.myskyscanner.rest

import com.pascalhow.myskyscanner.activities.search.FlightsSearch
import com.pascalhow.myskyscanner.rest.model.FlightsResult
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

object RestClient {

    private const val SESSION_CREATION_HEADER_LOCATION = "Location"
    private const val SEARCH_STATUS_UPDATES_COMPLETE = "Location"

    private val skyScannerApiService by lazy {
        SkyScannerApiService.create()
    }

    fun getSessionUrl(map: MutableMap<String, String>) : Observable<String> {
        return skyScannerApiService.createSession(map)
            .map { response ->
                response.headers().get(SESSION_CREATION_HEADER_LOCATION)
            }
    }

    fun search(sessionUrl: String, flightsSearch: FlightsSearch) : Observable<FlightsResult> {
        return skyScannerApiService.request(sessionUrl, flightsSearch.apiKey, pageIndex = "0", pageSize = "10")
            .repeatWhen { it.delay(500, TimeUnit.MILLISECONDS) }
            .takeWhile { result -> result.status == SEARCH_STATUS_UPDATES_COMPLETE }
    }

}
