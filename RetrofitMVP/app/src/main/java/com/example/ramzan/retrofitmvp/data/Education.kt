package com.example.ramzan.retrofitmvp.data.PersonInfoData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Education {

    @SerializedName("exam_name")
    @Expose
    var examName: String? = null
    @SerializedName("exam_year")
    @Expose
    var examYear: Int? = null
    @SerializedName("sub")
    @Expose
    var sub: String? = null
    @SerializedName("result")
    @Expose
    var result: Float? = null

}
