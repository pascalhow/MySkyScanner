package com.pascalhow.myskyscanner.rest.model

class Query {

    var locale: String? = null
    var locationSchema: String? = null
    var infants: String? = null
    var inboundDate: String? = null
    var originPlace: String? = null
    var cabinClass: String? = null
    var currency: String? = null
    var groupPricing: String? = null
    var country: String? = null
    var adults: String? = null
    var children: String? = null
    var outboundDate: String? = null
    var destinationPlace: String? = null

    override fun toString(): String {
        return "ClassPojo [Locale = $locale, LocationSchema = $locationSchema, Infants = $infants, InboundDate = $inboundDate, OriginPlace = $originPlace, CabinClass = $cabinClass, Currency = $currency, GroupPricing = $groupPricing, Country = $country, Adults = $adults, Children = $children, OutboundDate = $outboundDate, DestinationPlace = $destinationPlace]"
    }
}
