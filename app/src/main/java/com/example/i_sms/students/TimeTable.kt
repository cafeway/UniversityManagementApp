package com.example.i_sms.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.i_sms.R
import com.example.i_sms.adapters.TimeTableAdapter
import com.example.i_sms.api.Auth
import com.example.i_sms.api.StudentApi
import com.example.i_sms.models.GetResponse
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class TimeTable : AppCompatActivity() {
    // get the recycler view instance
    lateinit var applicationRV: RecyclerView
    lateinit var adapter: TimeTableAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)

        // hide acton bar
        supportActionBar?.hide()

        // get recycler view by id
        var recyclerView:RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        var adapter: TimeTableAdapter
        // Create Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(StudentApi::class.java)

        // create the get callback function that populates the data

        service.getTimeTable().enqueue(object: Callback<GetResponse>{
            override fun onResponse(call: Call<GetResponse>, response: Response<GetResponse>) {
                recyclerView.adapter =
                    response.body()?.let { TimeTableAdapter(this@TimeTable, it.timetable) }
            }

            override fun onFailure(call: Call<GetResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }
}