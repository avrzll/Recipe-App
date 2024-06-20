package com.avrzll.recipe.data.room

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.File

@Entity
data class IngredientsEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "ingredients_name")
    val name: String,

) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }
    companion object CREATOR : Parcelable.Creator<IngredientsEntity> {
        override fun createFromParcel(parcel: Parcel): IngredientsEntity {
            return IngredientsEntity(parcel)
        }

        override fun newArray(size: Int): Array<IngredientsEntity?> {
            return arrayOfNulls(size)
        }
    }
}