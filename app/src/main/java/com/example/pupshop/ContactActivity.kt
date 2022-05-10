package com.example.pupshop

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ContactActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        val thePup: Pup.Dog = intent?.extras?.getSerializable("pup") as Pup.Dog

        Toast.makeText(this, thePup.breed, Toast.LENGTH_SHORT).show()

        val btnCallNumber: Button = findViewById(R.id.btnCallNumber)
        btnCallNumber.setOnClickListener {
            val txtCallNumber: TextView = findViewById(R.id.textContact)
            val number: String = txtCallNumber.text.toString()

            val intent: Intent = Intent().apply {
                action = Intent.ACTION_DIAL
                data = Uri.parse("tel:" + number) }
            startActivity(intent)
        }



        // Initialize fragment
        // Initialize fragment
        val fragment: Fragment = MapFragment.newInstance(thePup.lat.toDouble(), thePup.lon.toDouble())
        // Open fragment


        // Open fragment
        supportFragmentManager
            .beginTransaction().replace(R.id.frame_layout, fragment)
            .commit()
    }
}