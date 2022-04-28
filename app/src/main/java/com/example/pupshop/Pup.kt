package com.example.pupshop

import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable

class Pup (
    val author: String = "",
    val title: String = "",
    val ISBN: String = "",
    val synopsis: String = "",
    val cover: String = "") : Serializable {

    companion object {
        fun getPups(filename: String, context: Context): ArrayList<Pup> {

            //create ArrayList of Pup objects
            val pupList = ArrayList<Pup>()

            try {
                //read json file
                val inputStream = context.assets.open(filename)
                val buffer = ByteArray(inputStream.available())
                inputStream.read(buffer)
                inputStream.close()

                //convert input to JSON
                val json = JSONObject(String(buffer, Charsets.UTF_8))
                val pups = json.getJSONArray("pups")

                //create new Pup objects from JSON and add to ArrayList
                for (i in 0 until pups.length())
                    pupList.add(Pup(
                        pups.getJSONObject(i).getString("Author"),
                        pups.getJSONObject(i).getString("Title"),
                        pups.getJSONObject(i).getString("ISBN"),
                        pups.getJSONObject(i).getString("Synopsis"),
                        pups.getJSONObject(i).getString("Cover")))
            }

            catch (e: JSONException) {
                e.printStackTrace()
            }

            //return the ArrayList of Pup objects
            return pupList
        }
    }
}