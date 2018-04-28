package com.example.ramzan.retrofitmvp.tools.network

import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo
import com.example.ramzan.retrofitmvp.tools.AppConstants
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PersonInfoRxAPI {

    @GET(""+ AppConstants.PERSON_INFO_DATA_API_URL)


    fun getPersonInfoRx(): Observable<PersonInfo>

    companion object {
        fun create(): PersonInfoRxAPI {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(AppConstants.PERSON_INFO_BASE_API_URL)
                    .build()

            return retrofit.create(PersonInfoRxAPI::class.java)
        }
    }

}