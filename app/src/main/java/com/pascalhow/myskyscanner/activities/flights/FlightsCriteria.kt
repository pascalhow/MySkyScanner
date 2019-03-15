package com.pascalhow.myskyscanner.activities.flights

data class FlightsCriteria(
    var country: String = "UK",
    var currency: String = "GBP",
    var locale: String = "en-GB",
    var originPlace: String = "EDI",
    var destinationPlace: String = "LOND",
    var outboundDate: String = "2019-05-30",
    var inboundDate: String = "2019-06-02",
    var adults: String = "1",
    var apiKey: String = "ss630745725358065467897349852985",
    var locationSchema: String = "sky"
)
