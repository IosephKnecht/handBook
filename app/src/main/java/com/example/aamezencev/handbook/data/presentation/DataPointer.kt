package com.example.aamezencev.handbook.data.presentation

import android.os.Parcel
import android.os.Parcelable
import com.example.aamezencev.handbook.data.HierarchyDSL

class DataPointer(val id: Long,
                  val startIndex: Int,
                  val finalIndex: Int,
                  val dataHierarchyId: Int,
                  val model: ThreeDimensionalModel) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readInt(),
            parcel.readParcelable(ThreeDimensionalModel::class.java.classLoader)) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeInt(startIndex)
        parcel.writeInt(finalIndex)
        parcel.writeInt(dataHierarchyId)
        parcel.writeParcelable(model, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DataPointer> {
        override fun createFromParcel(parcel: Parcel): DataPointer {
            return DataPointer(parcel)
        }

        override fun newArray(size: Int): Array<DataPointer?> {
            return arrayOfNulls(size)
        }
    }

    @HierarchyDSL
    class Builder() {
        var id = -1L
        var startIndex = -1
        var finalIndex = -1
        var dataHierarchyId = -1
        private lateinit var model: ThreeDimensionalModel

        fun model(block: ThreeDimensionalModel.Builder.() -> Unit) {
            model = ThreeDimensionalModel.Builder().apply(block).build()
        }

        fun build() = DataPointer(id, startIndex, finalIndex, dataHierarchyId, model)
    }
}