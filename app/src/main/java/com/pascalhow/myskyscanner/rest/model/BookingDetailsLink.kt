package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class BookingDetailsLink {

    @SerializedName("Method")
    var method: String? = null

    @SerializedName("Uri")
    var uri: String? = null

    @SerializedName("Body")
    var body: String? = null

    override fun toString(): String {
        return "ClassPojo [Method = $method, Uri = $uri, Body = $body]"
    }
}
