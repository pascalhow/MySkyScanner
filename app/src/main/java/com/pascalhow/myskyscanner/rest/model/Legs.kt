package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class Legs {

    @SerializedName("SegmentIds")
    var segmentIds: Array<String>? = null

    @SerializedName("Duration")
    var duration: String? = null

    @SerializedName("Arrival")
    var arrival: String? = null

    @SerializedName("Carriers")
    var carriers: Array<String>? = null

    @SerializedName("Directionality")
    var directionality: String? = null

    @SerializedName("OriginStation")
    var originStation: String? = null

    @SerializedName("Departure")
    var departure: String? = null

    @SerializedName("FlightNumbers")
    var flightNumbers: Array<FlightNumbers>? = null

    @SerializedName("JourneyMode")
    var journeyMode: String? = null

    @SerializedName("DestinationStation")
    var destinationStation: String? = null

    @SerializedName("Stops")
    var stops: Array<String>? = null

    @SerializedName("OperatingCarriers")
    var operatingCarriers: Array<String>? = null

    @SerializedName("Id")
    var id: String? = null

    override fun toString(): String {
        return "ClassPojo [SegmentIds = $segmentIds, Duration = $duration, Arrival = $arrival, Carriers = $carriers, Directionality = $directionality, OriginStation = $originStation, Departure = $departure, FlightNumbers = $flightNumbers, JourneyMode = $journeyMode, DestinationStation = $destinationStation, Stops = $stops, OperatingCarriers = $operatingCarriers, Id = $id]"
    }
}
