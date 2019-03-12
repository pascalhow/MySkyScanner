package com.pascalhow.myskyscanner.rest

import com.pascalhow.myskyscanner.rest.model.FlightsResult
import com.pascalhow.myskyscanner.rest.model.Legs
import com.pascalhow.myskyscanner.rest.model.Places
import com.pascalhow.myskyscanner.rest.model.Segments


class FlightResultsDataMapper(private val flightResult: FlightsResult) {

    var segmentsMap: MutableMap<String, Segments>? = null
    var legsMap: MutableMap<String, Legs>? = null
    var placesMap: MutableMap<String, Places>? = null

    fun buildMaps() {
        segmentsMap = buildSegmentsMap()
        legsMap = buildLegsMap()
        placesMap = buildPlacesMap()
    }

    private fun buildLegsMap(): MutableMap<String, Legs>? {
        val legsMap: MutableMap<String, Legs>? = HashMap()

        flightResult.legs?.forEach { leg ->
            leg.id?.let { id ->
                legsMap?.put(id, leg)
            }
        }
        return legsMap
    }

    private fun buildSegmentsMap(): MutableMap<String, Segments>? {
        val segmentsMap: MutableMap<String, Segments>? = HashMap()

        flightResult.segments?.forEach { segment ->
            segment.id?.let { id ->
                segmentsMap?.put(id, segment)
            }
        }
        return segmentsMap
    }

    private fun buildPlacesMap(): MutableMap<String, Places>? {
        val placesMap: MutableMap<String, Places>? = HashMap()

        flightResult.places?.forEach { place ->
            place.id?.let { id ->
                placesMap?.put(id, place)
            }
        }
        return placesMap
    }


}
