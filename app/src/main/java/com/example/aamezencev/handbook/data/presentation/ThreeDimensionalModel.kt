package com.example.aamezencev.handbook.data.presentation

import android.os.Parcel
import android.os.Parcelable
import com.example.aamezencev.handbook.data.HierarchyDSL

class ThreeDimensionalModel(val id: Long,
                            val modelArray: ByteArray) : Parcelable {


    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.createByteArray()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeByteArray(modelArray)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ThreeDimensionalModel> {
        override fun createFromParcel(parcel: Parcel): ThreeDimensionalModel {
            return ThreeDimensionalModel(parcel)
        }

        override fun newArray(size: Int): Array<ThreeDimensionalModel?> {
            return arrayOfNulls(size)
        }
    }

    @HierarchyDSL
    class Builder {
        var id = -1L
        var modelArray = byteArrayOf()

        fun build() = ThreeDimensionalModel(id, modelArray)
    }
}