package com.ever.four.catalog.util

import android.content.Context
import android.widget.ImageView
import com.ever.four.catalog.R
import com.squareup.picasso.Picasso

class ImageRetriever {
    private var urlImage: String
    private var imageView: ImageView

    constructor(urlImage: String, imageView: ImageView) {
        this.urlImage= urlImage
        this.imageView= imageView
    }

    fun fitImage() {
        Picasso.get().load(urlImage).fit().error(R.drawable.no_image_available).into(imageView)

    }

    fun addImage() {
        Picasso.get().load(urlImage).error(R.drawable.no_image_available).into(imageView)

    }
}