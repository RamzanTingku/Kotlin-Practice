package com.example.ramzan.retrofitmvp.mvp.PersonInfoMVP

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import com.example.ramzan.retrofitmvp.data.PersonInfoData.PersonInfo
import com.example.ramzan.retrofitmvp.tools.AppConstants
import com.example.ramzan.retrofitmvp.tools.PersonInfoTools.network.PersonInfoAPI
import com.example.ramzan.retrofitmvp.tools.PersonInfoTools.network.PersonInfoApiClient
import com.example.ramzan.retrofitmvp.tools.network.PersonInfoRxAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import org.json.JSONException
import org.json.JSONObject
import com.google.gson.Gson

class Model(var modelInterface: ModelInterface, var context: Context){

    var disposable: Disposable? = null

    val personInfoRxAPI by lazy {
        PersonInfoRxAPI.create()
    }

    val personInfoAPI by lazy {
        PersonInfoAPI.create()
    }

    // get mathod
    fun getPersonData() {

        try {
            /*val personInfoApiClient = PersonInfoApiClient().getPersonRetrofit()?.create(PersonInfoAPI::class.java)
            fetchDataWithCallBack(personInfoApiClient)*/

            fetchDataWithRxAndroid(personInfoRxAPI)
            fetchDataWithCallBack(personInfoAPI)
            fetchingDataWithHTTPCOnnection()


        } catch (e: Exception) {
        }

    }

    private fun fetchingDataWithHTTPCOnnection() {
        AsyncTaskExample().execute(AppConstants.PERSON_INFO_FULL_API_URL);
    }

    // saving mathod
    internal fun savePersonInfo(personInfo: PersonInfo, type: String) {

        val success = true

        if (success) {

            modelInterface.onPersonInfoSaveSuccess("Students data save successed")

        } else {

            modelInterface.onPersonInfoSaveFailed("Students data save failed")
        }
    }

    //fetching methods
    private fun fetchDataWithRxAndroid(personInfoRxAPI: PersonInfoRxAPI) {

        disposable = personInfoRxAPI?.getPersonInfoRx()
                        ?.subscribeOn(Schedulers.io())
                        ?.observeOn(AndroidSchedulers.mainThread())
                        ?.subscribe(
                                { result ->
                                    modelInterface.onPersonInfoLoadSuccess(result)
                                    Log.d("TestLoadRx",""+result.author)
                                },
                                { error -> modelInterface.onPersonInfoLoadFailed(error.message)

                                    Log.d("TestLoadRx",""+error.message)
                                }
                        )
    }

    private fun fetchDataWithCallBack(personInfoApiClient: PersonInfoAPI?) {

        val call = personInfoApiClient?.getPersonInfo()
        call?.enqueue(object : retrofit2.Callback<PersonInfo> {
            override fun onResponse(call: Call<PersonInfo>, response: Response<PersonInfo>) {


                if (response.code() == 200) {

                    val personInfo: PersonInfo? = response.body()

                    Log.d("TestLoadCall",""+personInfo?.author)
                    modelInterface.onPersonInfoLoadSuccess(personInfo)

                } else {

                    modelInterface.onPersonInfoLoadFailed("Data load failed")
                }
            }

            override fun onFailure(call: Call<PersonInfo>, t: Throwable) {

                modelInterface.onPersonInfoLoadFailed(t.message)

            }
        })
    }

    //fetching data with HTTP Connection through Asynctask
    inner class AsyncTaskExample: AsyncTask<String, String, String>() {
        override fun onPreExecute() {
            super.onPreExecute()
            //MyprogressBar.visibility = View.VISIBLE;

        }

        override fun doInBackground(vararg params: String): String? {
            var url: URL? = null
            var Result: String = "";
            try {
                url = URL(params[0])
                val httpURLConnection = url.openConnection() as HttpURLConnection
                httpURLConnection.requestMethod = "GET"
                httpURLConnection.connect()

                val ResponseCode: Int = httpURLConnection.responseCode;
                //Log.d("TestLoadHTTP",""+ResponseCode)
                if (ResponseCode == 200) {
                    val inputStream = httpURLConnection.inputStream
                    if (inputStream != null) {
                        Result = ConvertToString(inputStream);
                        //Log.d("TestLoadHTTP",""+Result)
                    }
                }

                return Result

            } catch (e: MalformedURLException) {
                e.printStackTrace()
                modelInterface.onPersonInfoLoadFailed(e.message)
            } catch (e: IOException) {
                e.printStackTrace()
                modelInterface.onPersonInfoLoadFailed(e.message)

            }

            return null
        }
        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            //MyprogressBar.visibility = View.INVISIBLE;
            if (result == "") {
               // my_text.text = "Network Error";
            } else {
                //my_text.text = result;
                try {
                    val personInfo = Gson().fromJson(result, PersonInfo::class.java)
                    modelInterface.onPersonInfoLoadSuccess(personInfo)
                    Log.d("TestLoadHTTP",""+personInfo.author)

                    /*val jsonObject = JSONObject(result)*/
                   /* val name = jsonObject.getString("author")
                    Log.d("TestLoadHTTP",""+jsonObject)*/
                } catch (e: JSONException) {
                    e.printStackTrace()
                    modelInterface.onPersonInfoLoadFailed(e.message)
                }
            }
        }
    }

    fun ConvertToString(inStream: InputStream): String {
        var Result: String = ""
        val isReader = InputStreamReader(inStream)
        var bReader = BufferedReader(isReader)
        var temp_str: String?
        try {
            while (true) {
                temp_str = bReader.readLine()
                if (temp_str == null) { break }
                Result += temp_str;
            }
        } catch(Ex: Exception) {
            modelInterface.onPersonInfoLoadFailed(Ex.message)
        }
        return Result
    }



    //disposing
    fun dipose(){
        disposable?.dispose()
    }

}
