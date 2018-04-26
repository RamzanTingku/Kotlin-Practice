package com.example.ramzan.recyclerviewpractice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.ramzan.recyclerviewpractice.adapter.CustomAdapter
import com.example.ramzan.recyclerviewpractice.adapter.PersonAdapter
import com.example.ramzan.recyclerviewpractice.adapter.PersonClickListener
import com.example.ramzan.recyclerviewpractice.data.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var personList: ArrayList<Person> = ArrayList()
    val adapter: PersonAdapter = PersonAdapter(personList, this)
    val customAdapter: CustomAdapter = CustomAdapter(personList, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPersonData()

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        recyclerView.adapter = customAdapter


        customAdapter.setOnPersonClickListener(object : PersonClickListener{
            override fun onPersonItemClickListener(person: Person?, view: View?) {

                when(view!!.id){

                    R.id.textViewAddress -> {
                        Toast.makeText(this@MainActivity,"Clicked: "+(person?.address ?: "No Data"),Toast.LENGTH_LONG).show()
                    }
                    R.id.textViewName -> {
                        Toast.makeText(this@MainActivity,"Clicked: "+ (person?.name ?: "No Data"),Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Toast.makeText(this@MainActivity,"Clicked: Nowhere",Toast.LENGTH_LONG).show()

                    }
                }
            }

        })
    }


    fun getPersonData() : ArrayList<Person> {

        personList.add(Person("Belal Khan", "Ranchi, India"))
        personList.add(Person("Belal Khan"))
        personList.add(Person())
        personList.add(Person("Belal Khan", "Ranchi, India"))
        personList.add(Person(null, "Ranchi, India"))
        personList.add(Person("Belal Khan", ""))


        return personList
    }
}
