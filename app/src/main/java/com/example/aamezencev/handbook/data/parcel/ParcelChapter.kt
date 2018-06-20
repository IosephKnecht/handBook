package com.example.aamezencev.handbook.data.parcel

import android.os.Parcel
import android.os.Parcelable

class ParcelChapter(override val name: String,
                    override val childList: List<IElement>,
                    override val text: String) : IElement, Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readArray(IElement::class.java.classLoader).toList()
                    .filter { it is IElement }
                    .map { it as IElement },
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelChapter> {
        override fun createFromParcel(parcel: Parcel): ParcelChapter {
            return ParcelChapter(parcel)
        }

        override fun newArray(size: Int): Array<ParcelChapter?> {
            return arrayOfNulls(size)
        }
    }
}