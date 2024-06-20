package com.avrzll.recipe.data.api

import android.os.Parcel
import android.os.Parcelable

data class FoodList(
    val id: Int,
    val name: String,
    val key: List<String>,
    val ingredients: List<String>,
    val steps: String,
    val image: String,
    val yt: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeStringList(key)
        parcel.writeStringList(ingredients)
        parcel.writeString(steps)
        parcel.writeString(image)
        parcel.writeString(yt)
    }

    // Fungsi ini digunakan untuk mendeskripsikan jenis konten khusus yang ditangani oleh Parcelable
    override fun describeContents(): Int {
        return 0
    }

    // Objek companion yang digunakan untuk membuat objek dari Parcel dan array
    companion object CREATOR : Parcelable.Creator<FoodList> {
        // Fungsi ini digunakan untuk membuat objek dari Parcel
        override fun createFromParcel(source: Parcel): FoodList {
            return FoodList(source)
        }

        // Fungsi digunakan untuk membuat array
        override fun newArray(size: Int): Array<FoodList?> {
            return arrayOfNulls(size)
        }
    }
}
