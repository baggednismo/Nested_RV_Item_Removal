package com.devinmartinolich.nestedrvitemremoval.models

class ChildItemModel(
    error_type: String,
    reported_date: String
) {

    var error_type: String
    var reported_date: String

    init {
        this.error_type = error_type
        this.reported_date = reported_date
    }
}