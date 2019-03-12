package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class Agents {

    @SerializedName("Status")
    var status: String? = null

    @SerializedName("Type")
    var type: String? = null

    @SerializedName("ImageUrl")
    var imageUrl: String? = null

    @SerializedName("Id")
    var id: String? = null

    @SerializedName("OptimisedForMobile")
    var optimisedForMobile: String? = null

    @SerializedName("Name")
    var name: String? = null

    override fun toString(): String {
        return "ClassPojo [Status = $status, Type = $type, ImageUrl = $imageUrl, Id = $id, OptimisedForMobile = $optimisedForMobile, Name = $name]"
    }
}
