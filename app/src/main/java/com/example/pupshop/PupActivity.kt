package com.example.pupshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class PupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pup)

        val thePup: Pup.Dog = intent?.extras?.getSerializable("pup") as Pup.Dog

        val imgPup: ImageView = findViewById(R.id.imgPup)
        val txtBread: TextView = findViewById(R.id.txtBread)
        val txtPrice: TextView = findViewById(R.id.txtPrice)
        val txtGender: TextView = findViewById(R.id.txtGender)
        val txtAge: TextView = findViewById(R.id.txtAge)
        val txtVaccinated: TextView = findViewById(R.id.txtVaccinated)
        val txtDescription: TextView = findViewById(R.id.txtDescription)
        val btnContact = findViewById<View>(R.id.btnContact) as Button

        var imgName = thePup.image.substring(0, thePup.image.length - 4)
        val resourceId =
            resources.getIdentifier(
                "@drawable/" +
                        "pup" + imgName, "drawable", packageName
            )
        imgPup.setImageResource(resourceId)
        txtBread.text = ": " + thePup.breed
        txtPrice.text = ": " + thePup.price
        txtGender.text = ": " + thePup.gender
        txtAge.text = ": " + thePup.age
        txtVaccinated.text = ": " + thePup.vaccinated
        txtDescription.text = ": " + thePup.description

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