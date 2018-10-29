package com.ever.four.catalog.adapter

import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.ever.four.catalog.R
import com.ever.four.catalog.entity.ItemEntity
import kotlinx.android.synthetic.main.activity_main.*
import java.net.URL
import java.net.UnknownHostException

class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {


    var list: List<ItemEntity>

    constructor(list: List<ItemEntity>) {
        this.list = list
    }

    class ViewHolder: RecyclerView.ViewHolder {

        var image: ImageView
        var title: TextView
        var detail: TextView

        constructor(itemView: View): super(itemView) {
            image = itemView.findViewById(R.id.image)
            title = itemView.findViewById(R.id.title)
            detail = itemView.findViewById(R.id.detail)

            itemView.setOnClickListener { v: View  ->
                var position: Int = getAdapterPosition()

            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.title.text = list.get(i).title
        viewHolder.detail.text = list.get(i).detail

        val thread = Thread(Runnable {
            try {
                val image = BitmapFactory.decodeStream(URL(list.get(i).image).openConnection().getInputStream());
                viewHolder.image.setImageBitmap(image)
            } catch (e: Exception) {
                viewHolder.image.setImageResource(R.drawable.no_image_available)
            }
        })
        thread.start();
    }

    override fun getItemCount(): Int {
        return list.size
    }
}