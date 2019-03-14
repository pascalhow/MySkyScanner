package com.pascalhow.myskyscanner.rest

import com.pascalhow.myskyscanner.rest.model.*


class FlightResultsDataMapper {

    var segmentsMap: MutableMap<String, Segments>? = null
    var legsMap: MutableMap<String, Legs>? = null
    var placesMap: MutableMap<String, Places>? = null
    var carriersMap: MutableMap<String, Carriers>? = null

    fun buildMaps(flightResult: FlightsResult) {
        segmentsMap = buildSegmentsMap(flightResult)
        legsMap = buildLegsMap(flightResult)
        placesMap = buildPlacesMap(flightResult)
        carriersMap = buildCarriersMap(flightResult)
    }

    private fun buildLegsMap(flightResult: FlightsResult): MutableMap<String, Legs>? {
        val legsMap: MutableMap<String, Legs>? = HashMap()

        flightResult.legs?.forEach { leg ->
            leg.id?.let { id ->
                legsMap?.put(id, leg)
            }
        }
        return legsMap
    }

    private fun buildSegmentsMap(flightResult: FlightsResult): MutableMap<String, Segments>? {
        val segmentsMap: MutableMap<String, Segments>? = HashMap()

        flightResult.segments?.forEach { segment ->
            segment.id?.let { id ->
                segmentsMap?.put(id, segment)
            }
        }
        return segmentsMap
    }

    private fun buildPlacesMap(flightResult: FlightsResult): MutableMap<String, Places>? {
        val placesMap: MutableMap<String, Places>? = HashMap()

        flightResult.places?.forEach { place ->
            place.id?.let { id ->
                placesMap?.put(id, place)
            }
        }
        return placesMap
    }

    private fun buildCarriersMap(flightResult: FlightsResult): MutableMap<String, Carriers>? {
        val carriersMap: MutableMap<String, Carriers>? = HashMap()

        flightResult.carriers?.forEach { carrier ->
            carrier.id?.let { id ->
                carriersMap?.put(id, carrier)
            }
        }
        return carriersMap
    }

}
