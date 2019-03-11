package com.pascalhow.myskyscanner.rest.model

import com.google.gson.annotations.SerializedName

class Places {

    @SerializedName("ParentId")
    var parentId: String? = null

    @SerializedName("Type")
    var type: String? = null

    @SerializedName("Id")
    var id: String? = null

    @SerializedName("Code")
    var code: String? = null

    @SerializedName("Name")
    var name: String? = null

    override fun toString(): String {
        return "ClassPojo [ParentId = $parentId, Type = $type, Id = $id, Code = $code, Name = $name]"
    }
}
