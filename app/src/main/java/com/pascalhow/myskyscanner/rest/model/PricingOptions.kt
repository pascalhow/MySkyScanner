package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class PricingOptions {

    @SerializedName("DeeplinkUrl")
    var deeplinkUrl: String? = null

    @SerializedName("Price")
    var price: String? = null

    @SerializedName("Agents")
    var agents: Array<String>? = null

    @SerializedName("QuoteAgeInMinutes")
    var quoteAgeInMinutes: String? = null

    override fun toString(): String {
        return "ClassPojo [DeeplinkUrl = $deeplinkUrl, Price = $price, Agents = $agents, QuoteAgeInMinutes = $quoteAgeInMinutes]"
    }
}
