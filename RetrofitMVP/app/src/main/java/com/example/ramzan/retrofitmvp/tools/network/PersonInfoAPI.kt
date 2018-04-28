package com.example.ramzan.retrofitmvp.tools.PersonInfoTools.network

import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo
import com.example.ramzan.retrofitmvp.tools.AppConstants
import retrofit2.Call
import retrofit2.http.GET

interface PersonInfoAPI {

    @GET(""+ AppConstants.PERSON_INFO_DATA_API_URL)
    fun getPersonInfo(): Call<PersonInfo>
}