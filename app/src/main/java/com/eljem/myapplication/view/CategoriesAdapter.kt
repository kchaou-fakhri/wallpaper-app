package com.eljem.myapplication.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eljem.myapplication.R
import com.eljem.myapplication.model.entity.Category
import com.eljem.myapplication.utils.ScaleImageView

class CategoriesAdapter(val context: Context, val categories: ArrayList<Category>) : RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    inner class ViewHolder(viewItem : View) : RecyclerView.ViewHolder(viewItem) {


        fun bind(category: Category){
            itemView.findViewById<TextView>(R.id.category_name).text = category.name
            itemView.findViewById<ScaleImageView>(R.id.image).setImageResource(category.image)

            itemView.setOnClickListener {
                context.let {
                    val intent = Intent(it, ListOfImagesActivity::class.java)
                    intent.putExtra("category", category.name.lowercase());
                    it.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.category_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories.get(position))
    }

    override fun getItemCount(): Int {
        return categories.size
    }

}