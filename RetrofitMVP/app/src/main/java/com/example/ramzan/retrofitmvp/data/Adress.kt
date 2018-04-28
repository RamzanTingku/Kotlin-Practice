package com.example.ramzan.retrofitmvp.data.PersonInfoData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Adress {

    @SerializedName("permanent")
    @Expose
    var permanent: String? = null
    @SerializedName("present")
    @Expose
    var present: String? = null

    constructor()

    constructor(permanent: String?, present: String?) {
        this.permanent = permanent
        this.present = present
    }


}
