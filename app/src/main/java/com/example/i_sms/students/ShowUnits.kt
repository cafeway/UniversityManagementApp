package com.example.i_sms.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.i_sms.R
import com.example.i_sms.adapters.TimeTableAdapter
import com.example.i_sms.adapters.UnitsAdapter
import com.example.i_sms.api.StudentApi
import com.example.i_sms.models.GetResponse
import com.example.i_sms.models.UnitDetails
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShowUnits : AppCompatActivity() {
    // get the recycler view instance
    lateinit var applicationRV: RecyclerView
    lateinit var adapter: TimeTableAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_units)

        // hide acton bar
        supportActionBar?.hide()

        // get recycler view by id
        var recyclerView: RecyclerView = findViewById(R.id.unitsRecyclerView)
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

        service.getUnits().enqueue(object: Callback<UnitDetails> {
            override fun onResponse(call: Call<UnitDetails>, response: Response<UnitDetails>) {
                recyclerView.adapter =
                    response.body()?.let { UnitsAdapter(this@ShowUnits, it.units) }
            }

            override fun onFailure(call: Call<UnitDetails>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })


    }
    }