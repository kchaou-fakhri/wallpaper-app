package com.eljem.myapplication

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.eljem.myapplication.model.entity.Photo
import com.eljem.myapplication.utils.ScaleImageView

class RecommendAdapter(val context: Context, val photos : ArrayList<Photo>) : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {


        fun bind(photo: Photo){
            Glide.with(context /* context */)
                .load(photo.url)
                .into(itemView.findViewById<ScaleImageView>(R.id.image))

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.recommend_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos.get(position))
    }

    override fun getItemCount(): Int {
        return photos.size
    }

}