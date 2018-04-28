package com.example.ramzan.retrofitmvp.data.PersonInfoData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Cv {

    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("dob")
    @Expose
    var dob: String? = null
    @SerializedName("name")
    @Expose
    var name: Name? = null
    @SerializedName("adress")
    @Expose
    var adress: Adress? = null
    @SerializedName("education")
    @Expose
    var education: List<Education>? = null

}
