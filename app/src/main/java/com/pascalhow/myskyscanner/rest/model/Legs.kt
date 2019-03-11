package com.pascalhow.myskyscanner.rest.model

class Legs {

    var segmentIds: Array<String>? = null
    var duration: String? = null
    var arrival: String? = null
    var carriers: Array<String>? = null
    var directionality: String? = null
    var originStation: String? = null
    var departure: String? = null
    var flightNumbers: Array<FlightNumbers>? = null
    var journeyMode: String? = null
    var destinationStation: String? = null
    var stops: Array<String>? = null
    var operatingCarriers: Array<String>? = null
    var id: String? = null

    override fun toString(): String {
        return "ClassPojo [SegmentIds = $segmentIds, Duration = $duration, Arrival = $arrival, Carriers = $carriers, Directionality = $directionality, OriginStation = $originStation, Departure = $departure, FlightNumbers = $flightNumbers, JourneyMode = $journeyMode, DestinationStation = $destinationStation, Stops = $stops, OperatingCarriers = $operatingCarriers, Id = $id]"
    }
}
