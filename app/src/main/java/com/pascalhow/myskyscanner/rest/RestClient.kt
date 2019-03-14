package com.pascalhow.myskyscanner.rest

import android.util.Log
import com.pascalhow.myskyscanner.rest.model.Itineraries
import io.reactivex.Observable

object RestClient {

    private const val SESSION_CREATION_HEADER_LOCATION = "Location"
    const val KEY_API_KEY = "apiKey"
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

                val dataMapper = FlightResultsDataMapper()
                dataMapper.buildMaps(result)
                FlightData(result.itineraries?.toList(), dataMapper)
            }
//            .repeatWhen { it.delay(500, TimeUnit.MILLISECONDS) }
//            .filter { result -> result.status == SEARCH_STATUS_UPDATES_COMPLETE }
    }

    data class FlightData(
        val itinerariesList: List<Itineraries>?,
        val flightResultsDataMapper: FlightResultsDataMapper
    )

}
