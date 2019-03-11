package com.pascalhow.myskyscanner.rest.model

class Currencies {

    var decimalSeparator: String? = null
    var thousandsSeparator: String? = null
    var symbolOnLeft: String? = null
    var spaceBetweenAmountAndSymbol: String? = null
    var symbol: String? = null
    var decimalDigits: String? = null
    var code: String? = null
    var roundingCoefficient: String? = null

    override fun toString(): String {
        return "ClassPojo [DecimalSeparator = $decimalSeparator, ThousandsSeparator = $thousandsSeparator, SymbolOnLeft = $symbolOnLeft, SpaceBetweenAmountAndSymbol = $spaceBetweenAmountAndSymbol, Symbol = $symbol, DecimalDigits = $decimalDigits, Code = $code, RoundingCoefficient = $roundingCoefficient]"
    }
}

