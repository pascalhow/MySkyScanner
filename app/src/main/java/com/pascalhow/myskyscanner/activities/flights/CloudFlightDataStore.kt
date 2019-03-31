package com.pascalhow.myskyscanner.activities.flights

import android.util.Log
import com.pascalhow.myskyscanner.rest.FlightResultsDataMapper
import com.pascalhow.myskyscanner.rest.RestClient
import com.pascalhow.myskyscanner.rest.model.Itineraries
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class CloudFlightDataStore(
    private val restClient: RestClient,
    private val flightResultsDataMapper: FlightResultsDataMapper
) : FlightDataStore {

    private val keyApiKey = "apiKey"
    private val searchComplete = "UpdatesComplete"
    private val searchPending = "UpdatesPending"
    private val retryDelay = 500L

    override fun flightEntityList(params: MutableMap<String, String>): Observable<ItinerariesMap> {
        return restClient.getSessionUrl(params)
            .flatMap { url -> restClient.search(url, params[keyApiKey]!!) }
            .flatMap { result ->
                if (result.status == searchPending) {
                    Log.d("Response Status", result.status)
                    flightResultsDataMapper.buildMaps(result)
                    val itinerariesMap = ItinerariesMap(result.itineraries?.toList(), flightResultsDataMapper)
                    Observable.just(itinerariesMap)
                } else {
                    Log.d("Response Status", result.status)
                    Observable.error(Exception())
                }
            }
            .retryWhen { it.delay(retryDelay, TimeUnit.MILLISECONDS) }
    }

    data class ItinerariesMap(
        val itinerariesList: List<Itineraries>?,
        val flightResultsDataMapper: FlightResultsDataMapper
    )

}
