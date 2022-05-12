package com.example.pupshop

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PupAdapter (
    private val context: MainActivity,
    private val pups: List<Pup.Dog>,
    private val breed: String) :
    RecyclerView.Adapter<PupAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtPrice: TextView = view.findViewById(R.id.txtPrice)
        val txtAge: TextView = view.findViewById(R.id.txtAge)
        val imgPup: ImageView = view.findViewById(R.id.imgPup)
    }

    override fun getItemCount() = pups.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.pup_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        if (pups.get(position).breed == breed) {
//
//        }
        val thePup = pups.get(position)
        holder.txtPrice.text = "Price: " + thePup.price
        holder.txtAge.text = "Age: " + thePup.age
        //Picasso.get().load(thePup.imageUrl).into(holder.imgFlag)

        var imgName = thePup.image.substring(0, thePup.image.length - 4)
        val resourceId =
            context.resources.getIdentifier(
                "@drawable/" +
                        "pup" + imgName, "drawable", context.packageName
            )
        holder.imgPup.setImageResource(resourceId)

        holder.itemView.setOnClickListener {
//                val pupDetailIntent: Intent = Intent(context,
//                    PupActivity::class.java).apply {
//                    putExtra("pup",Pup)
//                }
            val pupDetailIntent: Intent = Intent(
                context,
                PupActivity::class.java
            ).apply {
                putExtra("pup", thePup)
            }

            holder.itemView.context.startActivity(pupDetailIntent)
        }

    }
}