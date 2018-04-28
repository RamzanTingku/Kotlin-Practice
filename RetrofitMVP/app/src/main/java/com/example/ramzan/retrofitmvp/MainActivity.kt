package com.example.ramzan.retrofitmvp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.ramzan.retrofitmvp.data.PersonInfoData.Cv
import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo
import com.example.ramzan.retrofitmvp.mvp.PersonInfoMVP.Presenter
import com.example.ramzan.retrofitmvp.mvp.PersonInfoMVP.ViewData
import com.example.ramzan.retrofitmvp.tools.PersonInfoTools.adapter.CustomAdapter
import com.example.ramzan.retrofitmvp.tools.PersonInfoTools.adapter.CustomClickListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ViewData {


    var context: Context? = null
    var presenter: Presenter? = null
    var cvDataList = ArrayList<Cv>()
    var customAdapter: CustomAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = this@MainActivity
        presenter = Presenter(this, context as MainActivity)
        presenter!!.getPersonInfoList()

        setLinearRecyclerView()

    }

    private fun setLinearRecyclerView() {

        Log.d("TestAdaper",""+cvDataList.size)
        customAdapter = CustomAdapter(cvDataList, this)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)
        recyclerView.adapter = customAdapter
        setClickListener()
    }

    private fun setClickListener() {
        customAdapter?.setOnCustomCLickListener(object : CustomClickListener{
            override fun onCustomClickListener(cvInfo: Cv?, view: View?) {
                when(view!!.id){

                    R.id.textViewAddress -> {
                        Toast.makeText(this@MainActivity,"Clicked: "+(cvInfo?.adress?.permanent ?: "No Data"),Toast.LENGTH_LONG).show()
                    }
                    R.id.textViewName -> {
                        Toast.makeText(this@MainActivity,"Clicked: "+ (cvInfo?.name?.personName ?: "No Data"),Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        Toast.makeText(this@MainActivity,"Clicked: Nowhere",Toast.LENGTH_LONG).show()

                    }
                }

            }


        })
    }


    override fun startLoading() {
    }

    override fun stopLoading() {

    }

    override fun showError(message: String?) {
        Log.d("TestError",""+message)
    }

    override fun showPersonInfo(personInfo: PersonInfo) {

        for (cv in personInfo.cv!!){
            cvDataList.add(cv)
            customAdapter?.notifyDataSetChanged()
        }

        Log.d("TestSuccess",""+ (cvDataList[2].name?.personName))
    }

    override fun onPersonInfoSaveSuccess(message: String?) {

    }


}
