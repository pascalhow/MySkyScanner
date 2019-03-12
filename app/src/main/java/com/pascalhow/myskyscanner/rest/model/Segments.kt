package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class Segments {

    @SerializedName("Directionality")
    var directionality: String? = null

    @SerializedName("OriginStation")
    var originStation: String? = null

    @SerializedName("DepartureDateTime")
    var departureDateTime: String? = null

    @SerializedName("ArrivalDateTime")
    var arrivalDateTime: String? = null

    @SerializedName("JourneyMode")
    var journeyMode: String? = null

    @SerializedName("DestinationStation")
    var destinationStation: String? = null

    @SerializedName("OperatingCarrier")
    var operatingCarrier: String? = null

    @SerializedName("FlightNumber")
    var flightNumber: String? = null

    @SerializedName("Duration")
    var duration: String? = null

    @SerializedName("Id")
    var id: String? = null

    @SerializedName("ImageUrl")
    var carrier: String? = null

    override fun toString(): String {
        return "ClassPojo [Directionality = $directionality, OriginStation = $originStation, DepartureDateTime = $departureDateTime, ArrivalDateTime = $arrivalDateTime, JourneyMode = $journeyMode, DestinationStation = $destinationStation, OperatingCarrier = $operatingCarrier, FlightNumber = $flightNumber, Duration = $duration, Id = $id, Carrier = $carrier]"
    }
}
