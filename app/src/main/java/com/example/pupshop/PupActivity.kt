package com.example.pupshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class PupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pup)

        val thePup: Pup.Dog = intent?.extras?.getSerializable("pup") as Pup.Dog
        //val thePup = intent?.extras?.getSerializable("pup") as String

        Toast.makeText(this, thePup.breed, Toast.LENGTH_SHORT).show()

        val imgPup: ImageView = findViewById(R.id.imgPup)
        val txtBread: TextView = findViewById(R.id.txtBread)
        val txtPrice: TextView = findViewById(R.id.txtPrice)
        val txtGender: TextView = findViewById(R.id.txtGender)
        val txtAge: TextView = findViewById(R.id.txtAge)
        val textVaccinated: TextView = findViewById(R.id.textVaccinated)
        val textDescription: TextView = findViewById(R.id.textDescription)
        val btnContact = findViewById<View>(R.id.btnContact) as Button

        imgPup.setImageResource(R.drawable.pup2)
//        var imgName = thePup.image.substring(0, thePup.image.length - 4)
//        val resourceId =
//            context.resources.getIdentifier("@drawable/" +
//                    "pup" + imgName,"drawable", context.packageName)
//        imgPup.setImageResource(resourceId)

        txtBread.text = ": " + thePup.breed
        txtPrice.text = "Price: " + thePup.breed
        txtGender.text = "Gender: " + thePup.gender
        txtAge.text = "Age: " + thePup.age
        textVaccinated.text = "Vaccinated: " + thePup.vaccinated
        textDescription.text = "Description: " + thePup.description

        btnContact.setOnClickListener { view ->
            val pupDetailIntent: Intent = Intent(
                view.context,
                ContactActivity::class.java
            ).apply {
                putExtra("pup", thePup)
            }
            view.context.startActivity(pupDetailIntent)
        }



    }
}