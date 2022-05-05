package com.example.pupshop


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pup(
    val dogs: List<Dog>
) {
    data class Dog(
        val age: String,
        val breed: String,
        val contact: String,
        @SerializedName("Description")
        val description: String,
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
        val registeredBreeder: String,
        val type: String,
        val vaccinated: String,
        @SerializedName("wormedFlead?")
        val wormedFlead: String
    ): Serializable
}