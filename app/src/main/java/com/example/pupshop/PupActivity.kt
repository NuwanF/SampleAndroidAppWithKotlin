package com.example.pupshop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class PupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pup)


        val thePup: Pup.Dog = intent?.extras?.getSerializable("pup") as Pup.Dog
        //val thePup = intent?.extras?.getSerializable("pup") as String

        Toast.makeText(this, thePup.breed, Toast.LENGTH_SHORT).show()

        val txtTitle: TextView = findViewById(R.id.txtTitle)
        txtTitle.text = thePup.breed
    }
}