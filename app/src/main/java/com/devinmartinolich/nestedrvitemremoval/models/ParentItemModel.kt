package com.devinmartinolich.nestedrvitemremoval.models

class ParentItemModel(
    app_package: String,
    errors: ArrayList<ChildItemModel>
) {

    var app_package: String
    var errors: ArrayList<ChildItemModel>

    init {
        this.app_package = app_package
        this.errors = errors
    }
}