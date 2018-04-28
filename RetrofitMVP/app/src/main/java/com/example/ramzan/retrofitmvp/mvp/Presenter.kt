package com.example.ramzan.retrofitmvp.mvp.PersonInfoMVP

import android.content.Context
import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo

class Presenter(var view: ViewData, var context: Context) :  ModelInterface{

    var model: Model

    init {
        model = Model(this, context)
    }

    fun getPersonInfoList(){
        model.getPersonData()
        view.startLoading()
    }

    fun savePersonInfo(personInfo: PersonInfo, type: String){
        model.savePersonInfo(personInfo, type)
        view.startLoading()
    }


    override fun onPersonInfoLoadSuccess(personInfo: PersonInfo?) {

        view.stopLoading()
        view.showPersonInfo(personInfo)
    }

    override fun onPersonInfoSaveSuccess(message: String?) {

        view.stopLoading()
        view.onPersonInfoSaveSuccess(message)
    }

    override fun onPersonInfoLoadFailed(message: String?) {

        view.stopLoading()
        view.showError(message)
    }

    override fun onPersonInfoSaveFailed(message: String?) {

        view.stopLoading()
        view.showError(message)
    }

    override fun onStopFetching() {
        model.dipose()
    }

}