package com.pascalhow.myskyscanner.rest

import android.util.Log
import com.pascalhow.myskyscanner.rest.model.Itineraries
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

object FlightSearchRestClient : RestClient {

    const val KEY_API_KEY = "apiKey"
    private const val SESSION_CREATION_HEADER_LOCATION = "Location"
    private const val SEARCH_STATUS_UPDATES_COMPLETE = "UpdatesComplete"
    private const val RETRY_DELAY = 500L

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
    ): Observable<ItinerariesMap> {
        return skyScannerApiService.request(sessionUrl, apiKey, pageIndex = pageIndex, pageSize = pageSize)
            .flatMap { result ->
                if (result.status == SEARCH_STATUS_UPDATES_COMPLETE) {
                    Log.d("Response Status", result.status)
                    val dataMapper = FlightResultsDataMapper()
                    dataMapper.buildMaps(result)
                    Observable.just(ItinerariesMap(result.itineraries?.toList(), dataMapper))
                } else {
                    Log.d("Response Status", result.status)
                    Observable.error(Exception())
                }
            }
            .retryWhen { it.delay(RETRY_DELAY, TimeUnit.MILLISECONDS) }
    }

    data class ItinerariesMap(
        val itinerariesList: List<Itineraries>?,
        val flightResultsDataMapper: FlightResultsDataMapper
    )

}

