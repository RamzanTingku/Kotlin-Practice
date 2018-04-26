package com.example.ramzan.recyclerviewpractice.mvp

import com.example.ramzan.recyclerviewpractice.data.Person

interface PersonModelInterface {

     fun onPersonDataLoadSuccess(personList: List<Person>?)
     fun onPersonDataSaveSuccess(message: String?)
     fun onPersonDataLoadFailed(message: String?)
     fun onPersonDataSaveFailed(message: String?)
}