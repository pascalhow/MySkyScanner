package com.pascalhow.myskyscanner.activities.flights

import com.pascalhow.myskyscanner.rest.FlightSearchRestClient
import com.pascalhow.myskyscanner.rest.FlightSearchRestClient.ItinerariesMap
import com.pascalhow.myskyscanner.rest.RestClient
import io.reactivex.Observable

class FlightDetailsInteractor(private val restClient: RestClient) : Interactor {

    override fun getDataModelList(parameters: MutableMap<String, String>): Observable<List<TripDataModel>> {
        return restClient.getSessionUrl(parameters)
            .flatMap { url -> restClient.search(url, parameters[FlightSearchRestClient.KEY_API_KEY]!!) }
            .map { itinerariesMap -> createTripDataModelDataSet(itinerariesMap) }
    }

    private fun createTripDataModelDataSet(itinerariesMap: ItinerariesMap): List<TripDataModel> {
        val tripDataModelList = ArrayList<TripDataModel>()
        val legsMap = itinerariesMap.flightResultsDataMapper.legsMap
        val placesMap = itinerariesMap.flightResultsDataMapper.placesMap
        val carriersMap = itinerariesMap.flightResultsDataMapper.carriersMap

        //  Only getting the first carrier from the list
        itinerariesMap.itinerariesList?.forEach { itineraries ->
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
                TripDataModel(outboundFlight, inboundFlight, "$DUMMY_CURRENCY$price")
            )
        }
        return tripDataModelList
    }

    companion object {
        private const val DUMMY_CURRENCY = "£"
    }
}
