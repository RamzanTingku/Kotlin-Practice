package com.example.ramzan.retrofitmvp.tools.PersonInfoTools.adapter

import android.view.View
import com.example.ramzan.retrofitmvp.data.PersonInfoData.Cv
import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo

interface CustomClickListener {
    fun onCustomClickListener(cvInfo: Cv?, view: View?)
}