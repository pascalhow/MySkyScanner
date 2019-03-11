package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class FlightNumbers {

    @SerializedName("CarrierId")
    var carrierId: String? = null

    @SerializedName("FlightNumber")
    var flightNumber: String? = null

    override fun toString(): String {
        return "ClassPojo [CarrierId = $carrierId, FlightNumber = $flightNumber]"
    }
}
