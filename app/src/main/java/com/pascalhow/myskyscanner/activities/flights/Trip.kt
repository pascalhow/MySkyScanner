package com.pascalhow.myskyscanner.activities.flights

data class Trip(
    var departureTime: String?,
    var arrivalTime: String?,
    var origin: String?,
    var destination: String?,
    var carrier: String?,
    var stops: String?,
    var duration: String?
)
