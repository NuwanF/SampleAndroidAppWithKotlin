package com.example.pupshop

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PupAdapter (private val context: MainActivity,
                  private val pups: ArrayList<Pup>) :
    RecyclerView.Adapter<PupAdapter.PupViewHolder>() {

    class PupViewHolder (private val view: View) :
        RecyclerView.ViewHolder(view) {

        val txtAuthor: TextView = view.findViewById(R.id.txtAuthor)
        val txtISBN: TextView = view.findViewById(R.id.txtISBN)
        val txtSynopsis: TextView = view.findViewById(R.id.txtSynopsis)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val imgCover: ImageView = view.findViewById(R.id.imgCover)
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): PupViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).
        inflate(R.layout.pup_layout,
            parent, false)
        return PupViewHolder(adapterLayout)
    }


    override fun onBindViewHolder(holder: PupViewHolder, position: Int) {
        val thePup = pups.get(position)
        holder.txtTitle.text = thePup.title
        holder.txtAuthor.text = thePup.author
        holder.txtISBN.text = thePup.ISBN
        holder.txtSynopsis.text = thePup.synopsis
        val resourceId =
            context.resources.getIdentifier("@drawable/" +
                    thePup.cover,"drawable", context.packageName)
        holder.imgCover.setImageResource(resourceId)
        holder.itemView.setOnClickListener {
//            Toast.makeText(context, thePup.title,
//                Toast.LENGTH_SHORT).show()

            val pupDetailIntent: Intent = Intent(context,
                PupActivity::class.java).apply {
                putExtra("book", thePup)
//                putExtra("SURNAME", surname)
//                putExtra("AGE", age)
            }

            holder.itemView.context.startActivity(pupDetailIntent)

        }
    }


    override fun getItemCount() = pups.size
}
