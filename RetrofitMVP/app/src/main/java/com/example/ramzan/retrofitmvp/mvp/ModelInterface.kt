package com.example.ramzan.retrofitmvp.mvp.PersonInfoMVP

import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo

interface ModelInterface {

    fun onPersonInfoLoadSuccess(personInfoList: PersonInfo?)
    fun onPersonInfoSaveSuccess(message: String?)
    fun onPersonInfoLoadFailed(message: String?)
    fun onPersonInfoSaveFailed(message: String?)
    fun onStopFetching()
}