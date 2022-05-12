package com.example.pupshop


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pup(
    val dogs: List<Dog>
) {
    data class Dog(
        val address: String,
        val age: String,
        val breed: String,
        val contact: String,
        val contactName: String,
        @SerializedName("description")
        val description: String,
        val email: String,
        val gender: String,
        @SerializedName("healthGuarantee?")
        val healthGuarantee: String,
        val healthTested: String,
        val id: Int,
        val image: String,
        val imageUrl: String,
        val lat: String,
        val lon: String,
        @SerializedName("microchipped?")
        val microchipped: String,
        val originalUrl: String,
        val price: String,
        val registeredBreeder: String,
        val type: String,
        val vaccinated: String,
        @SerializedName("wormedFlead?")
        val wormedFlead: String,
    ): Serializable
}