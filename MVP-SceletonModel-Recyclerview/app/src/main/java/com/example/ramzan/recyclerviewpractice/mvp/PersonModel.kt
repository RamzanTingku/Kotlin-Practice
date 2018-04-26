package com.example.ramzan.recyclerviewpractice.mvp

import android.content.Context
import com.example.ramzan.recyclerviewpractice.data.Person

class PersonModel(var modelInterface:PersonModelInterface , var context: Context) {

    var persons: List<Person>? = null

    // TODO: get mathod
    fun getPersonData() {

        var isLoadSuccess = true

        if (isLoadSuccess){

            modelInterface.onPersonDataLoadSuccess(persons)

        }else{

            modelInterface.onPersonDataLoadFailed("Data load failed")

        }

    }

    // TODO: save mathod
    internal fun savePersonData(person: Person, type: String) {

        val success = true

        if (success) {

            modelInterface.onPersonDataSaveSuccess("Students data save successed")

        } else {

            modelInterface.onPersonDataSaveFailed("Students data save failed")
        }

    }

}