package com.pascalhow.myskyscanner.rest

import android.util.Log
import com.pascalhow.myskyscanner.rest.model.FlightsResult
import com.pascalhow.myskyscanner.rest.model.Itineraries
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

object RestClient {

    private const val SESSION_CREATION_HEADER_LOCATION = "Location"
    private const val SEARCH_STATUS_UPDATES_COMPLETE = "UpdatesComplete"

    private val skyScannerApiService by lazy {
        SkyScannerApiService.create()
    }

    fun getSessionUrl(map: MutableMap<String, String>): Observable<String> {
        return skyScannerApiService.createSession(map)
            .map { response ->
                response.headers().get(SESSION_CREATION_HEADER_LOCATION)
            }
    }

    fun search(sessionUrl: String, apiKey: String): Observable<FlightData> {
        return skyScannerApiService.request(sessionUrl, apiKey, pageIndex = "0", pageSize = "10")
            .map { result ->
                Log.d("Response Status", result.status)
                FlightData(result.itineraries?.toList(), FlightResultsDataMapper(result))
            }
//            .repeatWhen { it.delay(500, TimeUnit.MILLISECONDS) }
//            .filter { result -> result.status == SEARCH_STATUS_UPDATES_COMPLETE }
    }

    data class FlightData(
        val itinerariesList: List<Itineraries>?,
        val flightResultsDataMapper: FlightResultsDataMapper
    )

}
