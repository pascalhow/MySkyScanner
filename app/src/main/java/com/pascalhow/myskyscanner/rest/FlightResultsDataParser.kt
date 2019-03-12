package com.pascalhow.myskyscanner.rest

import com.pascalhow.myskyscanner.rest.model.FlightsResult
import com.pascalhow.myskyscanner.rest.model.Itineraries
import timber.log.Timber

class FlightResultsDataParser(private val flightResult: FlightsResult) {

    fun extractItineraries() {

        val itinerariesList: ArrayList<Itineraries> = ArrayList()

        flightResult.itineraries?.forEach { itinerary ->
            itinerariesList.add(itinerary)
        }

        Timber.d("Finished parsing itineraries")
    }
}
