package com.ever.four.catalog.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ever.four.catalog.R
import com.ever.four.catalog.activity.DescriptionActivity
import com.ever.four.catalog.entity.ItemEntity
import com.ever.four.catalog.util.ImageRetriever

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    protected var list: List<ItemEntity>

    constructor(list: List<ItemEntity>) {
        this.list = list
    }

    inner class ViewHolder: RecyclerView.ViewHolder {
        var image: ImageView
        var title: TextView
        var detail: TextView

        constructor(itemView: View): super(itemView) {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            detail = itemView.findViewById(R.id.detail)

            itemView.setOnClickListener { _: View  ->
                var position: Int = getAdapterPosition()
                var intentDetail = Intent(itemView.context, DescriptionActivity::class.java)
                intentDetail.putExtra("title", list[position].title)
                intentDetail.putExtra("description", list[position].description)
                intentDetail.putExtra("image", list[position].image)
                itemView.context.startActivity(intentDetail)
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.title.text = list[i].title
        viewHolder.detail.text = list[i].description

        var imageRetriever = ImageRetriever(list[i].image, viewHolder.image)
        imageRetriever.fitImage()

    }

    override fun getItemCount(): Int {
        return list.size
    }
}