package com.example.ramzan.recyclerviewpractice.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.ramzan.recyclerviewpractice.R
import com.example.ramzan.recyclerviewpractice.data.Person
import kotlinx.android.synthetic.main.list_layout.view.*

class PersonAdapter() : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>(){

    var personList: ArrayList<Person> = ArrayList()
    var context: Context? = null

    constructor(personList: ArrayList<Person>, context: Context) : this(){

        this.personList = personList
        this.context = context
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {

        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return PersonViewHolder(v)
    }

    override fun getItemCount(): Int {
        return personList?.size ?: 0
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {

       /* var noData = "No Data"
        val person: Person? = personList.get(position);

        holder.textViewName?.text  = person?.name ?: noData
        holder.textViewAddress?.text  = person?.address ?: noData*/

        val person: Person? = personList.get(position);
        holder.bind(person!!)

        var name = person.name
        var address = person.address

        holder.setOnPersonClickListener(object :PersonClickListener{
            override fun onPersonItemClickListener(view: Person?, position: View?) {

                Toast.makeText(context,"Clicked: "+name,Toast.LENGTH_LONG).show()
            }
        })


    }


    class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {


        /*@BindView(R.id.textViewName)
        lateinit var textViewName: TextView

        @BindView (R.id.textViewAddress)
        lateinit var textViewAddress: TextView

        init { ButterKnife.bind(this, itemView) }*/

        var noData = "No Data"

        var personClickListener:PersonClickListener?=null

        fun bind(person: Person) {
            itemView.textViewName?.text = person?.name ?: noData
            itemView.textViewAddress?.text = person?.address ?: noData
        }

        init {
            itemView.setOnClickListener(this)
        }

        fun setOnPersonClickListener(itemClickListener:PersonClickListener){
            this.personClickListener = itemClickListener
        }

        override fun onClick(view: View?) {
           /* this.personClickListener!!.onPersonItemClickListener(
                    view,
                    adapterPosition
            )*/
        }

    }


}
