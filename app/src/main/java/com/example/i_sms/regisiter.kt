package com.example.i_sms

import android.app.Activity
import android.os.Bundle
import android.util.JsonToken
import android.util.Log
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.i_sms.api.StudentApi
import com.example.i_sms.databinding.RegisiterBinding
import com.example.i_sms.errors.Reg_error
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.RequestBody
import okhttp3.ResponseBody
import org.json.JSONTokener
import java.lang.Integer.parseInt

class regisiter : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      setContentView(R.layout.activity_register)

        // spinners
        val spinner = findViewById<Spinner>(R.id.coursesSpinner)
        val schools_spinner = findViewById<Spinner>(R.id.schoolsSpinner)
        val county_spinner = findViewById<Spinner>(R.id.countySpinner)



        // creating a spinner adapater to show a list  courses options as options
        ArrayAdapter.createFromResource(
            this,R.array.courses,
            android.R.layout.simple_spinner_item
        ).also {
            adapter-> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        // creating a spinner adapaters show a list of schools as options
        ArrayAdapter.createFromResource(
            this,R.array.schools,
            android.R.layout.simple_spinner_item
        ).also {
                adapter-> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            schools_spinner.adapter = adapter
        }

        // creating a spinner adapaters show a list of county as options
        ArrayAdapter.createFromResource(
            this,R.array.County,
            android.R.layout.simple_spinner_item
        ).also {
                adapter-> adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            county_spinner.adapter = adapter
        }


        //get the button to init rest api call
        val button = findViewById<Button>(R.id.register)
        button.setOnClickListener(){
            Register()
        }
        val RegNo = findViewById<EditText>(R.id.RegNo).text.toString()
        val FirstName = findViewById<EditText>(R.id.FirstName).text.toString()
        val SecondName = findViewById<EditText>(R.id.SecondName).text.toString()
        val LastName = findViewById<EditText>(R.id.ThirdName).text.toString()
        val IdNo = findViewById<EditText>(R.id.Id).text.toString()
        val PhoneNumber = findViewById<EditText>(R.id.PhoneNumber).text.toString()
        val FathersName = findViewById<EditText>(R.id.FathersName).text.toString()
        val MothersName = findViewById<EditText>(R.id.MothersName).text.toString()
        val FathersPhone = findViewById<EditText>(R.id.FathersPhone).text.toString()
        val MothersPhone = findViewById<EditText>(R.id.MothersPhone).text.toString()
        val School = findViewById<Spinner>(R.id.schoolsSpinner).selectedItem.toString()
        val Course = findViewById<Spinner>(R.id.coursesSpinner).selectedItem.toString()
        val Gender = findViewById<Spinner>(R.id.countySpinner).selectedItem.toString()
    }

    //Function To register student to the remote server

    fun Register () {

        // getting values from my edittexts



        //spinnners for error message rendering
        val RegNo_ = findViewById<EditText>(R.id.RegNo)
        val FirstName_ = findViewById<EditText>(R.id.FirstName)
        val SecondName_ = findViewById<EditText>(R.id.SecondName)
        val LastName_ = findViewById<EditText>(R.id.ThirdName)
        val IdNo_ = findViewById<EditText>(R.id.Id)
        val PhoneNumber_ = findViewById<EditText>(R.id.PhoneNumber)
        val FathersName_ = findViewById<EditText>(R.id.FathersName)
        val MothersName_ = findViewById<EditText>(R.id.MothersName)
        val FathersPhone_ = findViewById<EditText>(R.id.FathersPhone)
        val MothersPhone_ = findViewById<EditText>(R.id.MothersPhone)

//        val id = parseInt(IdNo)

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(StudentApi::class.java)


        // Get data from fields
        val reg = findViewById<EditText>(R.id.RegNo)
        val FirstName = findViewById<EditText>(R.id.FirstName)
        val SecondName = findViewById<EditText>(R.id.SecondName)
        val LastName = findViewById<EditText>(R.id.ThirdName)
        val IdNo = findViewById<EditText>(R.id.Id)
        val PhoneNumber = findViewById<EditText>(R.id.PhoneNumber)
        val FathersName = findViewById<EditText>(R.id.FathersName)
        val MothersName = findViewById<EditText>(R.id.MothersName)
        val FathersPhone = findViewById<EditText>(R.id.FathersPhone)
        val MothersPhone = findViewById<EditText>(R.id.MothersPhone)
        val School = findViewById<Spinner>(R.id.schoolsSpinner)
        val Course = findViewById<Spinner>(R.id.coursesSpinner)
        val Gender = findViewById<Spinner>(R.id.countySpinner)
        // Create JSON using JSONObject
        val student_object = Student(
            reg.text.toString(),
            FirstName.text.toString(),
            SecondName.text.toString(),
            LastName.text.toString(),
            IdNo.text.toString(),
            PhoneNumber.text.toString(),
            School.selectedItem.toString(),
            Course.selectedItem.toString(),
            FathersPhone.text.toString(),
            MothersPhone.text.toString(),
            FathersName.text.toString(),
            MothersName.text.toString(),
            Gender.selectedItem.toString(),
            Ema

        )

        CoroutineScope(Dispatchers.IO).launch {
            // Do the POST request and get response
            val response = service.registerStudent(student_object)

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("success","the api call was successfull")
////                    Log.d("success","the api call was successfull")
                    val items =  response.body()?.string()
                    Log.d("yes","$items")
//                    items['Regbo'.toInt()]
//                    val a  = items?.length




                    val gson = Gson()
                    val b = gson.fromJson(items,Reg_error::class.java)
                    Log.d("aa","${b.Error}")


                    when(b.Field)
                    {
                        "RegNo"->RegNo_.setError(b.Error[0])
                        "FirstName"->FirstName_.setError(b.Error[0])
                        "SecondName"->SecondName_.setError(b.Error[0])
                        "FathersName"->FathersName.setError(b.Error[0])
                        "LastName"->LastName.setError(b.Error[0])
                        "IdNo"->IdNo.setError(b.Error[0])
                        "PhoneNumber"->PhoneNumber.setError(b.Error[0])
                        "MothersName"->MothersName.setError(b.Error[0])
                        "FathersPhone"->FathersPhone.setError(b.Error[0])
                        "MothersPhone"->MothersPhone.setError(b.Error[0])


                    }
//
//                    val bs = findViewById<Spinner>(R.id.schoolsSpinner)
//                    val c = bs.selectedItem.toString()
//                    Log.d("message",c)
//

                } else {

                    response.body()?.let { Log.e("RETROFIT_ERROR", it.string()) }

                }
            }
        }

    }



    fun parseJSON() {

        // Create Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(StudentApi::class.java)
        CoroutineScope(Dispatchers.IO).launch {

            // Do the GET request and get response
            val response = service.getStudents()

            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
//                    Log.d("success","the api call was successfull")
                    val items =  response.body()
                    if (items != null){
                        val Phone = items[0].School?:"null"
                        Log.d("data",items.toString())
                    }



//                    if (items != null){
//                        for (i in 0 until items.count()){
//                            val Fname = items[1].FirstName?:"N/A"
//                            Log.d("name",Fname)
//                        }
//                    }
//                    val items = response.body()
//                    if (items != null) {
//                        for (i in 0 until items.count()) {
//                            // ID
//                            val id = items[i].employeeId ?: "N/A"
//                            Log.d("ID: ", id)
//
//                            // Employee Name
//                            val employeeName = items[i].employeeName ?: "N/A"
//                            Log.d("Employee Name: ", employeeName)
//
//                            // Employee Salary
//                            val employeeSalary = items[i].employeeSalary ?: "N/A"
//                            Log.d("Employee Salary: ", employeeSalary)
//
//                            // Employee Age
//                            val employeeAge = items[i].employeeAge ?: "N/A"
//                            Log.d("Employee Age: ", employeeAge)
//
//                        }
//                    }

                } else {

                    Log.e("RETROFIT_ERROR", response.code().toString())

                }
            }
        }
    }

}


class SpinnnerActivity: Activity(),AdapterView.OnItemSelectedListener{
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        TODO("Not yet implemented")
        parent?.getItemAtPosition(position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}