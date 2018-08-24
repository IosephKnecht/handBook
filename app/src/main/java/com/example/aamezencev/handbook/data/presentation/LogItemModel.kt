package com.example.aamezencev.handbook.data.presentation

import android.os.Parcel
import android.os.Parcelable

class LogItemModel(val id: Long,
                   val log:String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(log)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LogItemModel> {
        override fun createFromParcel(parcel: Parcel): LogItemModel {
            return LogItemModel(parcel)
        }

        override fun newArray(size: Int): Array<LogItemModel?> {
            return arrayOfNulls(size)
        }
    }
}