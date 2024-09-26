package com.dev0ko.ewelp.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev0ko.ewelp.R
import com.dev0ko.ewelp.model.entity.Photo
import com.dev0ko.ewelp.utils.ScaleImageView
import com.dev0ko.ewelp.view.image.DisplayFullImageActivity

class RecommendAdapter(val context: Context, val photos : ArrayList<Photo>) : RecyclerView.Adapter<RecommendAdapter.ViewHolder>() {

    inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {


        fun bind(photo: Photo){
            Glide.with(context /* context */)
                .load(photo.url.regular)
                .into(itemView.findViewById<ScaleImageView>(R.id.image))
            itemView.setOnClickListener {
                context.let {
                    val intent = Intent(it, DisplayFullImageActivity::class.java)
                    intent.putExtra("photo", photo.url.full);
                    it.startActivity(intent)
                }
            }


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