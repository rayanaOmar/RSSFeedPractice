package com.example.rsspractic

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    lateinit var questions: MutableList<Question>
    lateinit var mainRV: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainRV = findViewById(R.id.rvMain)
        FetchQuestions().execute()
    }


    private inner class FetchQuestions : AsyncTask<Void, Void, MutableList<Question>>() {
        val parser = XMLParser()

        override fun doInBackground(vararg params: Void?): MutableList<Question> {
            val url = URL("https://stackoverflow.com/feeds")
            val urlConnection = url.openConnection() as HttpURLConnection
            questions =
                urlConnection.inputStream?.let { parser.parse(it) } as MutableList<Question>
            return questions
        }

        override fun onPostExecute(result: MutableList<Question>?) {
            super.onPostExecute(result)
            mainRV.adapter = RVadapter(result)
            mainRV.layoutManager = LinearLayoutManager(applicationContext)
        }
    }
}