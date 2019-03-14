package com.pascalhow.myskyscanner.activities.flights

import com.pascalhow.myskyscanner.rest.RestClient
import com.pascalhow.myskyscanner.rest.RestClient.FlightData
import io.reactivex.Observable

class FlightDetailsInteractor(private val restClient: RestClient) {

    fun getTripDataModelList(flightCriteriaParameters: MutableMap<String, String>): Observable<List<TripDataModel>> {
        return restClient.getSessionUrl(flightCriteriaParameters)
            .flatMap { url -> restClient.search(url, flightCriteriaParameters[RestClient.KEY_API_KEY]!!) }
            .map { flightData -> createTripDataModelDataSet(flightData) }
    }

    private fun createTripDataModelDataSet(flightData: FlightData): List<TripDataModel> {
        val tripDataModelList = ArrayList<TripDataModel>()
        val legsMap = flightData.flightResultsDataMapper.legsMap
        val placesMap = flightData.flightResultsDataMapper.placesMap
        val carriersMap = flightData.flightResultsDataMapper.carriersMap

        flightData.itinerariesList?.forEach { itineraries ->
            val outboundFlight = Flight(
                carriersMap?.get(legsMap?.get(itineraries.outboundLegId)?.carriers?.get(0))?.imageUrl,
                legsMap?.get(itineraries.outboundLegId)?.departure,
                legsMap?.get(itineraries.outboundLegId)?.arrival,
                placesMap?.get(legsMap?.get(itineraries.outboundLegId)?.originStation)?.code,
                placesMap?.get(legsMap?.get(itineraries.outboundLegId)?.destinationStation)?.code,
                carriersMap?.get(legsMap?.get(itineraries.outboundLegId)?.carriers?.get(0))?.name,
                legsMap?.get(itineraries.outboundLegId)?.stops?.getOrNull(0),
                legsMap?.get(itineraries.outboundLegId)?.duration
            )

            val inboundFlight = Flight(
                carriersMap?.get(legsMap?.get(itineraries.inboundLegId)?.carriers?.get(0))?.imageUrl,
                legsMap?.get(itineraries.inboundLegId)?.departure,
                legsMap?.get(itineraries.inboundLegId)?.arrival,
                placesMap?.get(legsMap?.get(itineraries.inboundLegId)?.originStation)?.code,
                placesMap?.get(legsMap?.get(itineraries.inboundLegId)?.destinationStation)?.code,
                carriersMap?.get(legsMap?.get(itineraries.inboundLegId)?.carriers?.get(0))?.name,
                legsMap?.get(itineraries.inboundLegId)?.stops?.getOrNull(0),
                legsMap?.get(itineraries.inboundLegId)?.duration
            )

            val price = itineraries.pricingOptions?.get(0)?.price

            tripDataModelList.add(
                TripDataModel(outboundFlight, inboundFlight, "£$price")
            )
        }
        return tripDataModelList
    }

}
