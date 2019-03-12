package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class FlightsResult {

    @SerializedName("Status")
    var status: String? = null

    @SerializedName("Legs")
    var legs: Array<Legs>? = null

    @SerializedName("Itineraries")
    var itineraries: Array<Itineraries>? = null

    @SerializedName("Segments")
    var segments: Array<Segments>? = null

    @SerializedName("Query")
    var query: Query? = null

    @SerializedName("SessionKey")
    var sessionKey: String?= null

    @SerializedName("Currencies")
    var currencies: Array<Currencies>? = null

    @SerializedName("Places")
    var places: Array<Places>? = null

    @SerializedName("ServiceQuery")
    var serviceQuery: ServiceQuery? = null

    @SerializedName("Carriers")
    var carriers: Array<Carriers>? = null

    @SerializedName("Agents")
    var agents: Array<Places>? = null

    override fun toString(): String {
        return "ClassPojo [Status = $status, Legs = $legs, Itineraries = $itineraries, Segments = $segments, Query = $query, SessionKey = $sessionKey, Currencies = $currencies, Places = $places, ServiceQuery = $serviceQuery, Carriers = $carriers, Agents = $agents]"
    }
}
