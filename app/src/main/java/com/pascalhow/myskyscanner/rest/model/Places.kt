package com.pascalhow.myskyscanner.rest.model

class Places {

    var parentId: String? = null
    var type: String? = null
    var id: String? = null
    var code: String? = null
    var name: String? = null

    override fun toString(): String {
        return "ClassPojo [ParentId = $parentId, Type = $type, Id = $id, Code = $code, Name = $name]"
    }
}
