package com.example.i_sms.students

import android.annotation.SuppressLint
import android.content.Intent
import android.hardware.biometrics.BiometricManager
import android.hardware.biometrics.BiometricManager.Authenticators.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.i_sms.R
import com.example.i_sms.adapters.TimeTableAdapter
import com.example.i_sms.api.Auth
import com.example.i_sms.api.StudentApi
import com.example.i_sms.models.GetResponse
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executor

class TimeTable : AppCompatActivity() {
    private lateinit var executor: Executor
    private lateinit var biometricPrompt: androidx.biometric.BiometricPrompt
    private lateinit var promptInfo: androidx.biometric.BiometricPrompt.PromptInfo
    // get the recycler view instance
    lateinit var applicationRV: RecyclerView
    lateinit var adapter: TimeTableAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time_table)

        // hide acton bar
        supportActionBar?.hide()

//        work with executor
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = androidx.biometric.BiometricPrompt(this@TimeTable,executor,biometricCallback)
        promptInfo = androidx.biometric.BiometricPrompt.PromptInfo.Builder()
            .setTitle("User Verification")
            .setSubtitle("Place your finger on the scanner")
            .setNegativeButtonText("Cancel")
            .build()

        // get the sign_attendance button by id
        val sign_button = findViewById<FloatingActionButton>(R.id.scan)

        // set a click listener that when clicked asks for biometric scan
        sign_button.setOnClickListener{
            val intent  = Intent(applicationContext,SignAttendance::class.java)
            startActivity(intent)
        }
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
    val biometricCallback = object : androidx.biometric.BiometricPrompt.AuthenticationCallback(){
        override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
            super.onAuthenticationError(errorCode, errString)
            Toast.makeText(this@TimeTable,"Authentication error", Toast.LENGTH_LONG).show()
        }

        override fun onAuthenticationSucceeded(result: androidx.biometric.BiometricPrompt.AuthenticationResult) {
            super.onAuthenticationSucceeded(result)
            Toast.makeText(this@TimeTable,"Your identity has been verified", Toast.LENGTH_LONG).show()

        }

    }

    private fun signUsingBiometrics() {
        Log.d("hey","sds");
    }
    // checks if a device has biometrics
    @SuppressLint("WrongConstant")
    private fun checkIfDeviceHasBiometrics (){
        val biometricManager = androidx.biometric.BiometricManager.from(this)
        when(biometricManager.canAuthenticate(BIOMETRIC_STRONG or BIOMETRIC_WEAK)) {
            BiometricManager.BIOMETRIC_SUCCESS-> {
                biometricPrompt.authenticate(promptInfo)
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED->{
                val intent = Intent(Settings.ACTION_BIOMETRIC_ENROLL).apply {
                    putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED, BIOMETRIC_STRONG or DEVICE_CREDENTIAL)
                }
            }
        }
    }
}