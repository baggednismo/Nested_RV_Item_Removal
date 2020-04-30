package com.devinmartinolich.nestedrvitemremoval.interfaces

interface ItemListener {
    fun onViewCard(position: Int)
    fun onRemoveCard(position: Int)
}