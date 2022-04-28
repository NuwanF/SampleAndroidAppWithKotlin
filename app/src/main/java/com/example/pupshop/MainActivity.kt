package com.example.pupshop

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
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

//        //populate data source using getBooks to read books.json
//        val pupList = Pup.getPups("pups.json", this)
//
//        //link to recycler view
//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = PupAdapter(this, pupList)

        //loadData("non")
    }

    private fun loadData(breed:String) {
        val service  = ServiceBuilder.buildService(CountryService::class.java)
        val requestCall = service.getPup()

        requestCall.enqueue(object : Callback<PupNew> {
            override fun onResponse(call: Call<PupNew>,
                                    response: Response<PupNew>
            ) {
                if (response.isSuccessful){
                    //process data
                    val pupList = response.body()!!
                    //if(breed != "non")
                        //pupList.dogs.filter { it.breed == breed }
                    var pp: List<PupNew.Dog> = pupList.dogs.filter { it.breed == breed }

                    val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
                    recyclerView.layoutManager = GridLayoutManager(this@MainActivity,2)
                    recyclerView.adapter = PupNewAdapter(this@MainActivity, pp, breed)

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
            override fun onFailure(call: Call<PupNew>, t: Throwable) {
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
        var arrGuest = arrayOf("Beagle", "2 adults", "3 adults", "4 adults", "5 adults", "More than 5 adults")

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