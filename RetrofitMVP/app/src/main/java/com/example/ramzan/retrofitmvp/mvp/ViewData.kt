package com.example.ramzan.retrofitmvp.mvp.PersonInfoMVP

import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo

interface ViewData {

    fun startLoading()
    fun stopLoading()
    fun showError(message: String?)
    fun showPersonInfo(personInfo: PersonInfo?)
    fun onPersonInfoSaveSuccess(message: String?)

}