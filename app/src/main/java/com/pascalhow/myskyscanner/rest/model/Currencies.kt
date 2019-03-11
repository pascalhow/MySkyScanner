package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class Currencies {

    @SerializedName("DecimalSeparator")
    var decimalSeparator: String? = null

    @SerializedName("ThousandsSeparator")
    var thousandsSeparator: String? = null

    @SerializedName("SymbolOnLeft")
    var symbolOnLeft: String? = null

    @SerializedName("SpaceBetweenAmountAndSymbol")
    var spaceBetweenAmountAndSymbol: String? = null

    @SerializedName("Symbol")
    var symbol: String? = null

    @SerializedName("DecimalDigits")
    var decimalDigits: String? = null

    @SerializedName("Code")
    var code: String? = null

    @SerializedName("RoundingCoefficient")
    var roundingCoefficient: String? = null

    override fun toString(): String {
        return "ClassPojo [DecimalSeparator = $decimalSeparator, ThousandsSeparator = $thousandsSeparator, SymbolOnLeft = $symbolOnLeft, SpaceBetweenAmountAndSymbol = $spaceBetweenAmountAndSymbol, Symbol = $symbol, DecimalDigits = $decimalDigits, Code = $code, RoundingCoefficient = $roundingCoefficient]"
    }
}

