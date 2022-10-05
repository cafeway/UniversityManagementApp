package com.example.i_sms

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.ui.AppBarConfiguration
import com.example.i_sms.api.Auth
import com.example.i_sms.errors.Reg_error
import com.example.i_sms.models.Model
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val actionBar = supportActionBar
        actionBar?.hide()
        // Login button
        val loginButton = findViewById<Button>(R.id.button)

        // click listener
        loginButton.setOnClickListener() {
            login()
        }

        // Redirect to create Account Activity
        val text = findViewById<TextView>(R.id.textView6)
        text.setOnClickListener{
            val intent = Intent(applicationContext,regisiter::class.java)
            startActivity(intent)
        }
    }

    fun login(){


        // get texts fields via id
        val EmailField = findViewById<EditText>(R.id.Email)
        val PasswordField = findViewById<EditText>(R.id.Password)

        // Create a login model with text from EditText
        val loginModel = Model(EmailField.text.toString(),PasswordField.text.toString())

        // Create Retrofit Instance
        val retrofit = Retrofit.Builder()
            .baseUrl("https://i-sms.herokuapp.com/")
            .addConverterFactory( GsonConverterFactory.create())
            .build()

        // Create Service
        val service = retrofit.create(Auth::class.java)

        // Launch an asynchrounous operation using co-routines
        CoroutineScope(Dispatchers.IO).launch {
            // Perform The post Request and get a response
            val response = service.Login(loginModel)
            withContext(Dispatchers.Main){
                // Test if the respsonce was successfull
                if (response.isSuccessful){
                    Log.d("Success","Request was success full")
                    val body = response.body()?.string()
                    Log.d("Success","$body")
//                     convert the json response obtained to gson and cast to a model
                    val gson = Gson()
                    val bodyGson = gson.fromJson(body, Reg_error::class.java)

                    Log.d("bodyJson", bodyGson.Field.toString())

//                     loop the body Gson for errors or success messages
                    val errorField = bodyGson.Field

                    when(errorField){
                        "Email"->EmailField.setError(bodyGson.Error[0])
                        "Password"->PasswordField.setError(bodyGson.Error[0])
                        "Success"->{
                            Toast.makeText(applicationContext, "Login was successful", Toast.LENGTH_LONG).show()
                            val intent = Intent(applicationContext, HomeScreen::class.java)
//                            val intent2 = Intent(applicationContext,EmployeeDash::class.java)
                            if (bodyGson.Error[0] == "student")
                            {
                                startActivity(intent)
                            }

                        }
                        "LoginError"->{
                            Toast.makeText(applicationContext, "${bodyGson.Error[0]}", Toast.LENGTH_LONG).show()
                            EmailField.setError(bodyGson.Error[0])
                            PasswordField.setError(bodyGson.Error[0])

                        }
                    }
                }else {
                    Log.e("RETROFIT_ERROR", response.code().toString())
                }
            }
        }

    }
}