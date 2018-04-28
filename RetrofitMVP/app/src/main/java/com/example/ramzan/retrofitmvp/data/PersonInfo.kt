package com.example.ramzan.retrofitmvp.data.PersonInfoData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PersonInfo {

    @SerializedName("subject")
    @Expose
    var subject: String? = null
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("cv")
    @Expose
    var cv: List<Cv>? = null


    constructor()

    constructor(subject: String?, author: String?, cv: List<Cv>?) {
        this.subject = subject
        this.author = author
        this.cv = cv
    }




}
