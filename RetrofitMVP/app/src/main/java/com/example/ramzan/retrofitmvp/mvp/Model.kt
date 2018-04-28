package com.example.ramzan.retrofitmvp.mvp.PersonInfoMVP

import android.content.Context
import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo
import android.util.Log
import com.example.ramzan.retrofitmvp.tools.PersonInfoTools.network.PersonInfoAPI
import com.example.ramzan.retrofitmvp.tools.PersonInfoTools.network.PersonInfoApiClient
import retrofit2.Call
import retrofit2.Response


class Model(var modelInterface: ModelInterface, var context: Context){

    var persons: List<PersonInfo>? = null
    var personInfo: PersonInfo? = null

    // TODO: get mathod
    fun getPersonData() {

        try {
            val personInfoApiClient = PersonInfoApiClient().getPersonRetrofit()?.create(PersonInfoAPI::class.java)
            val call = personInfoApiClient?.getPersonInfo()

            call?.enqueue(object : retrofit2.Callback<PersonInfo> {
                override fun onResponse(call: Call<PersonInfo>, response: Response<PersonInfo>) {


                    if (response.code() == 200) {

                        val personInfo:PersonInfo = response.body()

                        modelInterface.onPersonInfoLoadSuccess(personInfo)

                    } else {

                        modelInterface.onPersonInfoLoadFailed("Data load failed")
                    }
                }

                override fun onFailure(call: Call<PersonInfo>, t: Throwable) {

                    modelInterface.onPersonInfoLoadFailed(t.message)

                }
            })

        } catch (e: Exception) {
        }

    }

    // TODO: save mathod
    internal fun savePersonInfo(personInfo: PersonInfo, type: String) {

        val success = true

        if (success) {

            modelInterface.onPersonInfoSaveSuccess("Students data save successed")

        } else {

            modelInterface.onPersonInfoSaveFailed("Students data save failed")
        }

    }
}