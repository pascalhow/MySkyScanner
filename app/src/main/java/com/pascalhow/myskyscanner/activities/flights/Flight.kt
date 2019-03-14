package com.pascalhow.myskyscanner.activities.flights

data class Flight(
    var departureTime: String?,
    var arrivalTime: String?,
    var origin: String?,
    var destination: String?,
    var carrier: String?,
    var stops: String?,
    var duration: String?
)

data class TripDataModel(
    var outboundFlight: Flight,
    var inboundFlight: Flight,
    var price: String?
)

data class TripViewModel(
    var outboundTime : String?,
    var outboundAirline : String?,
    var outboundFlightType : String?,
    var outboundFlightDuration : String?,
    var inboundTime : String?,
    var inboundAirline : String?,
    var inboundFlightType : String?,
    var inboundFlightDuration : String?,
    var rating : String?,
    var price : String?,
    var airlineUrl : String?
)
