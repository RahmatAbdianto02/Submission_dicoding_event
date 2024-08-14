package com.dicoding.submissiondicodingevent.ui

import EventsAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.submissiondicodingevent.R
import com.dicoding.submissiondicodingevent.data.response.EventResponse
import com.dicoding.submissiondicodingevent.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EventsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_image)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EventsAdapter(listOf())
        recyclerView.adapter = adapter


        loadEvents()

    }

    private fun loadEvents(){
        val client = ApiConfig.getApiServie().getActiveEvents()
        client.enqueue(object : Callback<EventResponse>{
            override fun onResponse(call: Call<EventResponse>, response: Response<EventResponse>) {
                if (response.isSuccessful){
                    val events = response.body()?.listEvents ?: listOf()
                    adapter = EventsAdapter(events)
                    recyclerView.adapter = adapter
                } else {
                    Toast.makeText(this@MainActivity,"failed to load data",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<EventResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }

        })
    }
}