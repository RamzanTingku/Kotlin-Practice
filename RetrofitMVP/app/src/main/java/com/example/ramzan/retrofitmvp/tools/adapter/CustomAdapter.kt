package com.example.ramzan.retrofitmvp.tools.PersonInfoTools.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.ramzan.retrofitmvp.R
import com.example.ramzan.retrofitmvp.data.PersonInfoData.Cv
import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo
import java.util.ArrayList

class CustomAdapter() : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){

    var cvList: List<Cv> = ArrayList()
    var context: Context? = null
    var customClickListener: CustomClickListener? = null

    constructor(cvList: List<Cv>, context: Context) : this(){

        this.cvList = cvList
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder{

        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_layouot, parent, false)
        return CustomViewHolder(v)
    }

    override fun getItemCount(): Int {
        return cvList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        var noData = "No Data"
        val cvInfo: Cv = cvList[position]

        var name: String = cvInfo?.name?.personName.toString() ?: noData
        var address: String = cvInfo?.adress?.present.toString() ?: noData

        holder.textViewName?.text = name
        holder.textViewAddress.text = address

        var listener: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                customClickListener?.onCustomClickListener(cvInfo, v)
            }
        }

        holder.textViewName.setOnClickListener(listener)
        holder.textViewAddress.setOnClickListener(listener)
    }


    class CustomViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {

        @BindView(R.id.textViewName)
        lateinit var textViewName: TextView
        @BindView(R.id.textViewAddress)
        lateinit var textViewAddress: TextView

        init {
            ButterKnife.bind(this, itemview)
        }
    }

    fun getOnCustomClickListener() : CustomClickListener? {
        return customClickListener
    }

    fun setOnCustomCLickListener(itemClickListener: CustomClickListener){
        this.customClickListener = itemClickListener
    }
}