package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class Carriers {

    @SerializedName("ImageUrl")
    var imageUrl: String? = null

    @SerializedName("Id")
    var id: String? = null

    @SerializedName("Code")
    var code: String? = null

    @SerializedName("Name")
    var name: String? = null

    @SerializedName("DisplayCode")
    var displayCode: String? = null

    override fun toString(): String {
        return "ClassPojo [ImageUrl = $imageUrl, Id = $id, Code = $code, Name = $name, DisplayCode = $displayCode]"
    }
}
