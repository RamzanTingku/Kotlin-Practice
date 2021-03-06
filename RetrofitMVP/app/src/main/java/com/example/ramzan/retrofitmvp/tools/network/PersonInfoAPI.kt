package com.example.ramzan.retrofitmvp.tools.PersonInfoTools.network

import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo
import com.example.ramzan.retrofitmvp.tools.AppConstants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PersonInfoAPI {

    @GET(""+ AppConstants.PERSON_INFO_DATA_API_URL)
    fun getPersonInfo(): Call<PersonInfo>

    ////////////////////////////////////////////////////////////////////////////

    companion object {

        fun create(): PersonInfoAPI {

          val  retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(AppConstants.PERSON_INFO_BASE_API_URL)
                    .build()

            return retrofit.create(PersonInfoAPI::class.java)
        }

    }

}