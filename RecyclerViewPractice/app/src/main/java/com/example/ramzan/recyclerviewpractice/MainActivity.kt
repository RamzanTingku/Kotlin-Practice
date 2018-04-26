package com.example.ramzan.recyclerviewpractice

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.example.ramzan.recyclerviewpractice.R.id.recyclerView
import com.example.ramzan.recyclerviewpractice.adapter.CustomAdapter
import com.example.ramzan.recyclerviewpractice.adapter.PersonAdapter
import com.example.ramzan.recyclerviewpractice.adapter.PersonClickListener
import com.example.ramzan.recyclerviewpractice.data.Person
import com.example.ramzan.recyclerviewpractice.mvp.PersonPresenter
import com.example.ramzan.recyclerviewpractice.mvp.ViewPerson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewPerson {

    var personList: ArrayList<Person> = ArrayList()
    val adapter: PersonAdapter = PersonAdapter(personList, this)
    val customAdapter: CustomAdapter = CustomAdapter(personList, this)

    var context: Context? = null
    var presenter: PersonPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity
        presenter = PersonPresenter(this, context as MainActivity)
        presenter!!.getPersonDataList()

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


    override fun startLoading() {
        Toast.makeText(this,"Data is loading...",Toast.LENGTH_SHORT).show()
    }

    override fun stopLoading() {
        Toast.makeText(this,"Data loading finished",Toast.LENGTH_SHORT).show()
    }

    override fun showError(message: String?) {
        Toast.makeText(this,""+message,Toast.LENGTH_SHORT).show()
    }

    override fun showPersonList(personList: List<Person>?) {

        Log.d("MVP", "" + (personList?.size ?: 0))

        customAdapter.notifyDataSetChanged()
    }

}
