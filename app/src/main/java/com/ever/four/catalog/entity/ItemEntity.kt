package com.ever.four.catalog.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class ItemEntity(@SerializedName("image") val image: String,
                      @SerializedName("title") val title: String,
                      @SerializedName("description") val description: String): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(title)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ItemEntity> {
        override fun createFromParcel(parcel: Parcel): ItemEntity {
            return ItemEntity(parcel)
        }

        override fun newArray(size: Int): Array<ItemEntity?> {
            return arrayOfNulls(size)
        }
    }
}