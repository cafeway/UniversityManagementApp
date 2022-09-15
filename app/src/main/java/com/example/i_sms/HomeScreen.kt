package com.example.i_sms

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.i_sms.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_home_screen)


        val  cardView = findViewById<CardView>(R.id.card1)
        cardView.setOnClickListener(){

           val toast= Toast.makeText(applicationContext,"hwlll",Toast.LENGTH_LONG)
            toast.show()
            val intent = Intent(this,regisiter::class.java).apply {

            }
            startActivity(intent)
        }
    }


}