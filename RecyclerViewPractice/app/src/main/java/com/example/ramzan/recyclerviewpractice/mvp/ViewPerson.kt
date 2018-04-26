package com.example.ramzan.recyclerviewpractice.mvp

import com.example.ramzan.recyclerviewpractice.data.Person

interface ViewPerson {

    fun startLoading()
    fun stopLoading()
    fun showError(message: String?)
    fun showPersonList(personList: List<Person>?)

}