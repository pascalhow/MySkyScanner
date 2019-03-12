package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class Itineraries {

    @SerializedName("InboundLegId")
    var inboundLegId: String? = null

    @SerializedName("OutboundLegId")
    var outboundLegId: String? = null

    @SerializedName("BookingDetailsLink")
    var bookingDetailsLink: BookingDetailsLink? = null

    @SerializedName("PricingOptions")
    var pricingOptions: Array<PricingOptions>? = null

    override fun toString(): String {
        return "ClassPojo [InboundLegId = $inboundLegId, BookingDetailsLink = $bookingDetailsLink, OutboundLegId = $outboundLegId, PricingOptions = $pricingOptions]"
    }
}
