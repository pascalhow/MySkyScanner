package com.pascalhow.myskyscanner.activities.flights

import com.pascalhow.myskyscanner.rest.FlightResultsDataMapper

class TripsPresenter(private val flightResultsDataMapper: FlightResultsDataMapper) {

    fun getTripViewModel(outboundLegId: String?, inboundLegId: String?): TripsViewModel {
        flightResultsDataMapper.buildMaps()
        val legsMap = flightResultsDataMapper.legsMap
        val placesMap = flightResultsDataMapper.placesMap


        val outboundTrip = Trip(
            legsMap?.get(outboundLegId)?.departure,
            legsMap?.get(outboundLegId)?.arrival,
            placesMap?.get(legsMap?.get(outboundLegId)?.originStation)?.code,
            placesMap?.get(legsMap?.get(outboundLegId)?.destinationStation)?.code,
            legsMap?.get(outboundLegId)?.carriers?.get(0),
            legsMap?.get(outboundLegId)?.stops?.getOrNull(0),
            legsMap?.get(outboundLegId)?.duration
        )

        val inboundTrip = Trip(
            legsMap?.get(inboundLegId)?.departure,
            legsMap?.get(inboundLegId)?.arrival,
            placesMap?.get(legsMap?.get(inboundLegId)?.originStation)?.code,
            placesMap?.get(legsMap?.get(inboundLegId)?.destinationStation)?.code,
            legsMap?.get(inboundLegId)?.carriers?.get(0),
            legsMap?.get(inboundLegId)?.stops?.getOrNull(0),
            legsMap?.get(inboundLegId)?.duration
        )

        return TripsViewModel(outboundTrip, inboundTrip, price = "£40")
    }
}

data class TripsViewModel(
    var outboundTrip: Trip,
    var inboundTrip: Trip,
    var price: String?
)
