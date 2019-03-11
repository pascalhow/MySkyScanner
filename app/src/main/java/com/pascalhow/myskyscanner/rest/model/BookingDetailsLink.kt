package com.pascalhow.myskyscanner.rest.model

class BookingDetailsLink {

    var method: String? = null
    var uri: String? = null
    var body: String? = null

    override fun toString(): String {
        return "ClassPojo [Method = $method, Uri = $uri, Body = $body]"
    }
}
