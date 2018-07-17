package com.example.aamezencev.handbook.data.parcel

import android.os.Parcel
import android.os.Parcelable
import com.example.aamezencev.handbook.data.presentation.IHierarchy

class ParcelHierarchy(override val name: String,
                      override val childList: List<IHierarchy>,
                      override val text: String) : IElement<IHierarchy>, Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readArray(IElement::class.java.classLoader).toList()
                    .filter { it is IElement<*> }
                    .map { it as IHierarchy },
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(text)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelHierarchy> {
        override fun createFromParcel(parcel: Parcel): ParcelHierarchy {
            return ParcelHierarchy(parcel)
        }

        override fun newArray(size: Int): Array<ParcelHierarchy?> {
            return arrayOfNulls(size)
        }
    }
}