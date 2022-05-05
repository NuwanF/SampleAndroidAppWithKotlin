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
        class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            val txtCountry: TextView = view.findViewById(R.id.txtCountry)
            val txtCases: TextView = view.findViewById(R.id.txtCases)
            val imgFlag: ImageView = view.findViewById(R.id.imgFlag)
        }

        override fun getItemCount() = pups.size

        override fun onCreateViewHolder(parent: ViewGroup,
                                        viewType: Int): ViewHolder {
            return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.pup_layout, parent, false))
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            if(pups.get(position).breed == breed){
                val thePup = pups.get(position)
                holder.txtCountry.text = "Puppy: " + thePup.breed
                holder.txtCases.text = "Age: " + thePup.age
                //Picasso.get().load(thePup.imageUrl).into(holder.imgFlag)

                var imgName = thePup.image.substring(0, thePup.image.length - 4)
                val resourceId =
                    context.resources.getIdentifier("@drawable/" +
                            "pup" + imgName,"drawable", context.packageName)
                holder.imgFlag.setImageResource(resourceId)

                holder.itemView.setOnClickListener {
//                val pupDetailIntent: Intent = Intent(context,
//                    PupActivity::class.java).apply {
//                    putExtra("pup",Pup)
//                }
                    val pupDetailIntent: Intent = Intent(context,
                        PupActivity::class.java).apply {
                        putExtra("pup", thePup)
//                putExtra("SURNAME", surname)
//                putExtra("AGE", age)
                    }

                    holder.itemView.context.startActivity(pupDetailIntent)
                }
            }

        }
    }