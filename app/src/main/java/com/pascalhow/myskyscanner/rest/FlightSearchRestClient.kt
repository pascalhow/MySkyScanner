package com.pascalhow.myskyscanner.rest

import android.util.Log
import com.pascalhow.myskyscanner.rest.model.Itineraries
import io.reactivex.Observable

object FlightSearchRestClient : RestClient {

    const val KEY_API_KEY = "apiKey"
    private const val SESSION_CREATION_HEADER_LOCATION = "Location"
    private const val SEARCH_STATUS_UPDATES_COMPLETE = "UpdatesComplete"

    private val skyScannerApiService by lazy {
        SkyScannerApiService.create()
    }

    override fun getSessionUrl(map: MutableMap<String, String>): Observable<String> {
        return skyScannerApiService.createSession(map)
            .map { response ->
                response.headers().get(SESSION_CREATION_HEADER_LOCATION)
            }
    }

    override fun search(sessionUrl: String, apiKey: String): Observable<FlightData> {
        return skyScannerApiService.request(sessionUrl, apiKey, pageIndex = "0", pageSize = "10")
            .flatMap { result ->
                if (result.status == SEARCH_STATUS_UPDATES_COMPLETE) {
                    Log.d("Response Status", result.status)
                    val dataMapper = FlightResultsDataMapper()
                    dataMapper.buildMaps(result)
                    Observable.just(FlightData(result.itineraries?.toList(), dataMapper))
                } else {
                    Log.d("Response Status", result.status)
                    Observable.error(Exception())
                }
            }
            .retry()
    }

    data class FlightData(
        val itinerariesList: List<Itineraries>?,
        val flightResultsDataMapper: FlightResultsDataMapper
    )

}

interface RestClient {
    fun getSessionUrl(map: MutableMap<String, String>): Observable<String>
    fun search(sessionUrl: String, apiKey: String): Observable<FlightSearchRestClient.FlightData>
}
