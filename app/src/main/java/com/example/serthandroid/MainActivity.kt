package com.example.serthandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private var dataReq: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        while(1==1){
//            testButton()
//
//        }
    }


    fun testButton(){
        run("http://82.179.4.198:257/test?id=" + "sds" + "&lon=" +"sds")
        textView6.text = dataReq.toString()
    }

    //ФУнкция обновелния переменной храняций ответ от вервера
    private fun parse(response: String?) {
        println(response)
        this.dataReq = response.toString()
    }

    //отправка GET запроса
    fun run(url: String) {
        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).enqueue(
            object : Callback {
                override fun onFailure(call: Call, e: IOException) {}
                //override fun onResponse(call: Call, response: Response) = println(response.body()?.string())
                override fun onResponse(call: Call, response: Response){
                    if (response.isSuccessful) {
                        val body = response.body()?.string()
                        parse(body)
                    }

                }

            })
    }

}
