package com.dev0ko.ewelp.presentation.ui.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.dev0ko.ewelp.R
import com.dev0ko.ewelp.presentation.ui.image.ListOfImagesActivity

class ColorAdapter(val context: Context, val colors: ArrayList<com.dev0ko.ewelp.data.entity.Color>) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {


        fun bind(color : com.dev0ko.ewelp.data.entity.Color){
            itemView.findViewById<CardView>(R.id.colors).setCardBackgroundColor(Color.parseColor(color.value))

            itemView.setOnClickListener {
                context.let {
                    val intent = Intent(it, ListOfImagesActivity::class.java)
                    intent.putExtra("category", color.name.lowercase())
                    it.startActivity(intent)
                }
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.color_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(colors.get(position))
    }

    override fun getItemCount(): Int {
       return colors.size
    }

}