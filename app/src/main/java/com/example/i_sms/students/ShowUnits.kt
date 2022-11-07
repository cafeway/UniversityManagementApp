package com.example.i_sms.students

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.i_sms.R
import com.example.i_sms.adapters.TimeTableAdapter
import com.example.i_sms.adapters.UnitsAdapter
import com.example.i_sms.api.StudentApi
import com.example.i_sms.models.GetResponse
import com.example.i_sms.models.UnitDetails
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ShowUnits : AppCompatActivity() {
    // get the recycler view instance
    lateinit var applicationRV: RecyclerView
    lateinit var adapter: UnitsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_units)

        // hide acton bar
        supportActionBar?.hide()

        // get recycler view by id
        var recyclerView: RecyclerView = findViewById(R.id.unitsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        var adapter: UnitsAdapter
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
                adapter = response.body()?.units?.let { UnitsAdapter(this@ShowUnits,it) }!!
                recyclerView.adapter = adapter
                adapter.setOnItemClickListener(object : UnitsAdapter.OnItemClickListener{
                    override fun onItemClicked(positon: Int) {
                        Log.d("hell","sdsds")
//                        Toast.makeText(applicationContext, "hey",Toast.LENGTH_SHORT).show()
                    }

                })
            }

            override fun onFailure(call: Call<UnitDetails>, t: Throwable) {
                t.printStackTrace()
            }


        })
        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener{
            Toast.makeText(this,"units registered successfully",Toast.LENGTH_SHORT).show()

        }

    }
    }