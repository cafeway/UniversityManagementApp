package com.example.i_sms

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.i_sms.databinding.ActivityHomeScreenBinding
import com.example.i_sms.students.ShowUnits
import com.example.i_sms.students.TimeTable

class HomeScreen : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_home_screen)

        //getting time table image
        val timeTableImage = findViewById<ImageView>(R.id.accomodation_icon)
        timeTableImage.setOnClickListener{
            val intent = Intent(this,TimeTable::class.java)
            startActivity(intent)
        }

        //getting time table image
        val academicicon = findViewById<ImageView>(R.id.academcs_icon)
        academicicon.setOnClickListener{
            val intent = Intent(this,ShowUnits::class.java)
            startActivity(intent)
        }
        //
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