package com.dev0ko.ewelp.presentation.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev0ko.ewelp.R
import com.dev0ko.ewelp.data.entity.Photo
import com.dev0ko.ewelp.presentation.ui.image.DisplayFullImageActivity
import com.dev0ko.ewelp.utils.Constants
import com.dev0ko.ewelp.utils.ScaleImageView
import com.dev0ko.ewelp.utils.isTablet
import com.squareup.picasso.Picasso
import kotlin.random.Random

class ImagesAdapter(val context: Context, val photos : ArrayList<Photo>) : RecyclerView.Adapter<ImagesAdapter.ViewHolder>() {

    inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {


        fun bind(photo: Photo){


            Picasso.get().load(photo.url.small)
                .into(itemView.findViewById<ScaleImageView>(R.id.image))
            if (isTablet(context)){
                itemView.layoutParams.height = Random.nextInt(500,950)
            }else{
                itemView.layoutParams.height = Random.nextInt(400,750)

            }
            itemView.setOnClickListener {
                context.let {
                    val intent = Intent(it, DisplayFullImageActivity::class.java)
                    if (!photo.url.full.isNullOrEmpty()){
                        intent.putExtra(Constants.PHOTO, photo.url.full)
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