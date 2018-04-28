package com.example.ramzan.retrofitmvp.data.PersonInfoData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Name {

    @SerializedName("person_name")
    @Expose
    var personName: String? = null
    @SerializedName("father_name")
    @Expose
    var fatherName: String? = null
    @SerializedName("mother_name")
    @Expose
    var motherName: String? = null

}
