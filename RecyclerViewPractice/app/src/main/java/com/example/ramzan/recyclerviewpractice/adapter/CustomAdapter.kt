package com.example.ramzan.recyclerviewpractice.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.ramzan.recyclerviewpractice.R
import com.example.ramzan.recyclerviewpractice.data.Person

class CustomAdapter() : RecyclerView.Adapter<CustomAdapter.CustomViewHolder>(){

    var personList: ArrayList<Person> = ArrayList()
    var context: Context? = null
    var personClickListener:PersonClickListener?=null

    constructor(personList: ArrayList<Person>, context: Context) : this(){

        this.personList = personList
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {

        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return CustomViewHolder(v)
    }

    override fun getItemCount(): Int {
        return personList?.size ?: 0
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        var noData = "No Data"
        val person: Person? = personList.get(position)

        var name = person?.name ?: noData
        var address = person?.address ?: noData

        holder.textViewName?.text  = name
        holder.textViewAddress?.text  = address

        var listener: View.OnClickListener = object : View.OnClickListener{
            override fun onClick(v: View?) {
                personClickListener?.onPersonItemClickListener(person,v)
            }
        }

        holder.textViewName.setOnClickListener(listener);
        holder.textViewAddress.setOnClickListener(listener);

    }


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){


        @BindView(R.id.textViewName)
        lateinit var textViewName: TextView

        @BindView (R.id.textViewAddress)
        lateinit var textViewAddress: TextView

        init { ButterKnife.bind(this, itemView) }


    }


    fun getOnItemClickListener() : PersonClickListener? {

        return personClickListener
    }

    fun setOnPersonClickListener(itemClickListener:PersonClickListener){
        this.personClickListener = itemClickListener
    }


}