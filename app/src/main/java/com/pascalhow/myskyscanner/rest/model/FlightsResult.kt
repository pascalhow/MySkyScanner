package com.pascalhow.myskyscanner.rest.model

class FlightsResult {

    var status: String? = null
    var legs: Array<Legs>? = null
    var itineraries: Array<Itineraries>? = null
    var query: Query? = null
    var sessionKey: String?= null
    var currencies: Array<Currencies>? = null
    var places: Array<Places>? = null
    var serviceQuery: ServiceQuery? = null

    override fun toString(): String {
        return "ClassPojo [Status = $status, Legs = $legs, Itineraries = $itineraries, Query = $query, SessionKey = $sessionKey, Currencies = $currencies, Places = $places, ServiceQuery = $serviceQuery]"
    }
}

