package com.pascalhow.myskyscanner.rest.model

class PricingOptions {

    var deeplinkUrl: String? = null
    var price: String? = null
    var agents: Array<String>? = null
    var quoteAgeInMinutes: String? = null

    override fun toString(): String {
        return "ClassPojo [DeeplinkUrl = $deeplinkUrl, Price = $price, Agents = $agents, QuoteAgeInMinutes = $quoteAgeInMinutes]"
    }
}
