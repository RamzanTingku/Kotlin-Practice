package com.example.ramzan.retrofitmvp.mvp.PersonInfoMVP

import android.content.Context
import android.util.Log
import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo
import com.example.ramzan.retrofitmvp.tools.PersonInfoTools.network.PersonInfoAPI
import com.example.ramzan.retrofitmvp.tools.PersonInfoTools.network.PersonInfoApiClient
import com.example.ramzan.retrofitmvp.tools.network.PersonInfoRxAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response


class Model(var modelInterface: ModelInterface, var context: Context){

    var persons: List<PersonInfo>? = null
    var personInfo: PersonInfo? = null
    var disposable: Disposable? = null

    val personInfoRxAPI by lazy {
        PersonInfoRxAPI.create()
    }

    val personInfoAPI by lazy {
        PersonInfoAPI.create()
    }

    // TODO: get mathod
    fun getPersonData() {

        try {
            /*val personInfoApiClient = PersonInfoApiClient().getPersonRetrofit()?.create(PersonInfoAPI::class.java)
            fetchDataWithCallBack(personInfoApiClient)*/

            fetchDataWithRxAndroid(personInfoRxAPI)
            fetchDataWithCallBack(personInfoAPI)


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



    //fetching methods
    private fun fetchDataWithRxAndroid(personInfoRxAPI: PersonInfoRxAPI) {

        disposable = personInfoRxAPI?.getPersonInfoRx()
                        ?.subscribeOn(Schedulers.io())
                        ?.observeOn(AndroidSchedulers.mainThread())
                        ?.subscribe(
                                { result ->
                                    modelInterface.onPersonInfoLoadSuccess(result)
                                    Log.d("TestLoadRx",""+result.author)
                                },
                                { error -> modelInterface.onPersonInfoLoadFailed(error.message)

                                    Log.d("TestLoadRx",""+error.message)
                                }
                        )
    }

    private fun fetchDataWithCallBack(personInfoApiClient: PersonInfoAPI?) {

        val call = personInfoApiClient?.getPersonInfo()
        call?.enqueue(object : retrofit2.Callback<PersonInfo> {
            override fun onResponse(call: Call<PersonInfo>, response: Response<PersonInfo>) {


                if (response.code() == 200) {

                    val personInfo: PersonInfo? = response.body()

                    Log.d("TestLoadCall",""+personInfo?.author)
                    modelInterface.onPersonInfoLoadSuccess(personInfo)

                } else {

                    modelInterface.onPersonInfoLoadFailed("Data load failed")
                }
            }

            override fun onFailure(call: Call<PersonInfo>, t: Throwable) {

                modelInterface.onPersonInfoLoadFailed(t.message)

            }
        })
    }

    //disposing
    fun dipose(){
        disposable?.dispose()
    }

}