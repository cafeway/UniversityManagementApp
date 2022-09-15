package com.example.i_sms

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.i_sms.databinding.RegisiterBinding

class regisiter : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

      setContentView(R.layout.activity_register)

        // instantiate the spinner
        val spinner = findViewById<Spinner>(R.id.coursesSpinner)
        ArrayAdapter.createFromResource(
            this,R.array.courses,
            android.R.layout.simple_dropdown_item_1line
        )
    }


}