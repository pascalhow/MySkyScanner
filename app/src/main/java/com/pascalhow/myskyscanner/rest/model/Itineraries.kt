package com.pascalhow.myskyscanner.rest.model

class Itineraries {

    var inboundLegId: String? = null
    var bookingDetailsLink: BookingDetailsLink? = null
    var outboundLegId: String? = null
    var pricingOptions: Array<PricingOptions>? = null

    override fun toString(): String {
        return "ClassPojo [InboundLegId = $inboundLegId, BookingDetailsLink = $bookingDetailsLink, OutboundLegId = $outboundLegId, PricingOptions = $pricingOptions]"
    }
}
