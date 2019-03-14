package com.pascalhow.myskyscanner.activities.flights

import com.pascalhow.myskyscanner.rest.RestClient
import com.pascalhow.myskyscanner.rest.RestClient.FlightData
import io.reactivex.Observable

class FlightDetailsInteractor {

    private val restClient: RestClient = RestClient

    fun getTripDataModel(flightCriteriaParameters: MutableMap<String, String>): Observable<List<TripDataModel>> {
        return restClient.getSessionUrl(flightCriteriaParameters)
            .flatMap { url -> restClient.search(url, flightCriteriaParameters["apiKey"]!!) }
            .map { flightData -> createTripDataModelDataSet(flightData) }
    }

    private fun createTripDataModelDataSet(flightData: FlightData): List<TripDataModel> {
        val tripsPresenter = TripsPresenter(flightData.flightResultsDataMapper)
        val tripDataModelList = ArrayList<TripDataModel>()

        flightData.itinerariesList?.forEach { itineraries ->
            tripDataModelList.add(
                tripsPresenter.getTripDataModel(
                    itineraries.outboundLegId,
                    itineraries.inboundLegId
                )
            )
        }
        return tripDataModelList
    }
}
