package com.eljem.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ColorAdapter(val context: Context, val colors : ArrayList<String>) : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {


        fun bind(color : String){
            itemView.findViewById<TextView>(R.id.color).setBackgroundColor(Color.parseColor(color))

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