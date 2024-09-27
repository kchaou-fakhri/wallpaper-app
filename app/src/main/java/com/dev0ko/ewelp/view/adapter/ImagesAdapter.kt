package com.dev0ko.ewelp.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev0ko.ewelp.R
import com.dev0ko.ewelp.model.entity.Photo
import com.dev0ko.ewelp.utils.ScaleImageView
import com.dev0ko.ewelp.view.image.DisplayFullImageActivity
import com.squareup.picasso.Picasso
import kotlin.random.Random

class ImagesAdapter(val context: Context, val photos : ArrayList<Photo>) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {


        fun bind(photo: Photo){


            Picasso.get().load(photo.url.small)
                .into(itemView.findViewById<ScaleImageView>(R.id.image))
            itemView.layoutParams.height = Random.nextInt(400,750)
//            Glide.with(context /* context */)
//                .load(photo.url.small)
//                .into(itemView.findViewById<ScaleImageView>(R.id.image))
            itemView.setOnClickListener {
                context.let {
                    val intent = Intent(it, DisplayFullImageActivity::class.java)
                    if (!photo.url.full.isNullOrEmpty()){
                        intent.putExtra("photo", photo.url.full)
                    }

                    it.startActivity(intent)
                }
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(photos.get(position))
    }

    override fun getItemCount(): Int {
        return photos.size
    }

}