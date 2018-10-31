package com.ever.four.catalog.entity

import com.google.gson.annotations.SerializedName

data class ItemEntity(@SerializedName("image") val image: String,
                      @SerializedName("title") val title: String,
                      @SerializedName("description") val description: String) {
}