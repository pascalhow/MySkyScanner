package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class Query {

    @SerializedName("Locale")
    var locale: String? = null

    @SerializedName("LocationSchema")
    var locationSchema: String? = null

    @SerializedName("Infants")
    var infants: String? = null

    @SerializedName("InboundDate")
    var inboundDate: String? = null

    @SerializedName("OriginPlace")
    var originPlace: String? = null

    @SerializedName("CabinClass")
    var cabinClass: String? = null

    @SerializedName("Currency")
    var currency: String? = null

    @SerializedName("GroupPricing")
    var groupPricing: String? = null

    @SerializedName("Country")
    var country: String? = null

    @SerializedName("Adults")
    var adults: String? = null

    @SerializedName("Children")
    var children: String? = null

    @SerializedName("OutboundDate")
    var outboundDate: String? = null

    @SerializedName("DestinationPlace")
    var destinationPlace: String? = null

    override fun toString(): String {
        return "ClassPojo [Locale = $locale, LocationSchema = $locationSchema, Infants = $infants, InboundDate = $inboundDate, OriginPlace = $originPlace, CabinClass = $cabinClass, Currency = $currency, GroupPricing = $groupPricing, Country = $country, Adults = $adults, Children = $children, OutboundDate = $outboundDate, DestinationPlace = $destinationPlace]"
    }
}
