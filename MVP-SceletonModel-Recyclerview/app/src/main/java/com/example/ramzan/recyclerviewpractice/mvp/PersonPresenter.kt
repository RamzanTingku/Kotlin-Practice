package com.example.ramzan.recyclerviewpractice.mvp

import android.content.Context
import android.util.Log
import com.example.ramzan.recyclerviewpractice.data.Person

class PersonPresenter( var view: ViewPerson, var context: Context) : PersonModelInterface {

    var model: PersonModel

    init {
        model = PersonModel(this, context)
    }


    fun getPersonDataList() {
        model.getPersonData()
        view.startLoading()
    }

    fun savePersonDataList(person: Person, type: String) {
        model.savePersonData(person, type)
        view.startLoading()
    }


    override fun onPersonDataLoadSuccess(personList: List<Person>?) {
        view.stopLoading()
        view.showPersonList(personList)    }


    override fun onPersonDataSaveSuccess(message: String?) {
        view.stopLoading()
        Log.d("MVP: ", "" + message)
    }


    override fun onPersonDataLoadFailed(message: String?) {
        view.stopLoading()
        Log.d("MVP: ", "" + message)
    }


    override fun onPersonDataSaveFailed(message: String?) {
        view.stopLoading()
        Log.d("MVP: ", "" + message)
    }


}
