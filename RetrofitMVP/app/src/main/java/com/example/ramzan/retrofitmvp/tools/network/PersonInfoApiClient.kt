package com.example.ramzan.retrofitmvp.tools.PersonInfoTools.network

import com.example.ramzan.retrofitmvp.tools.AppConstants
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class PersonInfoApiClient(){

    private var retrofit: Retrofit? = null

    fun getPersonRetrofit(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(AppConstants.PERSON_INFO_BASE_API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }

}

