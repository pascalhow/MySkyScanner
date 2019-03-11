package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class ServiceQuery {

    @SerializedName("DateTime")
    var dateTime: String? = null

    override fun toString(): String {
        return "ClassPojo [DateTime = $dateTime]"
    }
}
