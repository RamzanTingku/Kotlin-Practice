package com.example.ramzan.recyclerviewpractice.data

data class Person(val name: String?){

    var address: String? = null

    constructor(): this(name = null){

    }

    constructor(name: String?, address: String?) : this(name){
        this.address = address
    }

}