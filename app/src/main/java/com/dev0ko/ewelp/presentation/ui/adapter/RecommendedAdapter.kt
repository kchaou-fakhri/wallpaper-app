package com.dev0ko.ewelp.presentation.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dev0ko.ewelp.R
import com.dev0ko.ewelp.data.entity.Photo
import com.dev0ko.ewelp.presentation.ui.image.DisplayFullImageActivity
import com.dev0ko.ewelp.utils.Constants
import com.dev0ko.ewelp.utils.ScaleImageView

class RecommendedAdapter(val context: Context, val photos : ArrayList<Photo>) : RecyclerView.Adapter<RecommendedAdapter.ViewHolder>() {

    inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {


        fun bind(photo: Photo){
            Glide.with(context)
                .load(photo.url.regular)
                .into(itemView.findViewById<ScaleImageView>(R.id.image))
            itemView.setOnClickListener {
                context.let {
                    val intent = Intent(it, DisplayFullImageActivity::class.java)
                    intent.putExtra(Constants.PHOTO, photo.url.full);
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