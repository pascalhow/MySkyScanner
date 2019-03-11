package com.pascalhow.myskyscanner.activities.flights

data class FlightDetails(
    var outboundTime:String = "15:35 - 17:00",
    var outboundAirline: String = "EasyJet",
    var outboundFlightType: String = "Direct",
    var outboundFlightDuration: String = "2h25m",
    var inboundTime:String = "15:35 - 17:00",
    var inboundAirline: String = "EasyJet",
    var inboundFlightType: String = "Direct",
    var inboundFlightDuration: String = "2h25m",
    var rating: String = "10",
    var price: String = "£40",
    var airlineUrl: String = "via easyJet.com"
)
