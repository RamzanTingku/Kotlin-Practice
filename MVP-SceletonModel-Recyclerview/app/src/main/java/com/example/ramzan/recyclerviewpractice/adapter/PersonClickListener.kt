package com.example.ramzan.recyclerviewpractice.adapter

import android.view.View
import com.example.ramzan.recyclerviewpractice.data.Person

interface PersonClickListener {
    fun onPersonItemClickListener(person: Person?, view: View?)
}