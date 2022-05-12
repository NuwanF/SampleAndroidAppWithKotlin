package com.example.pupshop

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

lateinit var alertDialog: AlertDialog.Builder
lateinit var listView: ListView
lateinit var adapter: ArrayAdapter<String>
lateinit var dialog: AlertDialog
lateinit var txtSearch: TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtSearch = findViewById<TextView>(R.id.txtSearch)

        loadData("Any")
    }

    private fun loadData(breed:String) {
        val service  = ServiceBuilder.buildService(CountryService::class.java)
        val requestCall = service.getPup()

        requestCall.enqueue(object : Callback<Pup> {
            override fun onResponse(call: Call<Pup>,
                                    response: Response<Pup>
            ) {
                if (response.isSuccessful){
                    //process data
                    val pupList = response.body()!!
                    //if(breed != "non")
                        //pupList.dogs.filter { it.breed == breed }
                    var filteredPups: List<Pup.Dog> = if(breed=="Any") pupList.dogs else pupList.dogs.filter { it.breed == breed }
                    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                    recyclerView.layoutManager = GridLayoutManager(this@MainActivity,2)
                    recyclerView.adapter = PupAdapter(this@MainActivity, filteredPups , breed)

                }else{
                    //output alert
                    AlertDialog.Builder(this@MainActivity)
                        .setTitle("API error")
                        .setMessage("Response, but something went wrong ${response.message()}")
                        .setPositiveButton(android.R.string.ok) { _, _ -> }
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show()
                }
            }
            override fun onFailure(call: Call<Pup>, t: Throwable) {
                //process failure
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("API error")
                    .setMessage("No response, and something went wrong $t")
                    .setPositiveButton(android.R.string.ok) { _, _ -> }
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show()
            }
        })
    }

    fun dropDown(view: View){
        var arrGuest = arrayOf("Any", "Beagle", "Boxer", "Pomeranian", "German Shepherd")

        alertDialog = AlertDialog.Builder(this)
        val rowList: View = layoutInflater.inflate(R.layout.my_row, null)
        listView = rowList.findViewById(R.id.listView)

           adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrGuest)

        listView.adapter = adapter
        adapter.notifyDataSetChanged()
        alertDialog.setView(rowList)
        dialog = alertDialog.create()
        dialog.show()

        listView.setOnItemClickListener { parent, view, position, id ->
            txtSearch!!.text = arrGuest.get(position)
            loadData(arrGuest.get(position).toString())

            dialog.dismiss()
        }
    }
}