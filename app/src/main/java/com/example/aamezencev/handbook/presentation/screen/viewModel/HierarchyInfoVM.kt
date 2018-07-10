package com.example.aamezencev.handbook.presentation.screen.viewModel

import android.os.Parcel
import android.os.Parcelable
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.DataPointer
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel
import com.example.aamezencev.handbook.presentation.screen.HierarchyScreenContract

class HierarchyInfoVM() : AbstractViewModel(), HierarchyScreenContract.ViewModel,Parcelable {
    override var description: String? = null
        get() = field
        set(value) {
            field = value
            notifyChange()
        }

    override var pointerList: List<DataPointer> = listOf()

    constructor(parcel: Parcel) : this() {
        parcel.readString()
        parcel.readArrayList(DataPointer::class.java.classLoader)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeArray(pointerList.toTypedArray())
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<HierarchyInfoVM> {
        override fun createFromParcel(parcel: Parcel): HierarchyInfoVM {
            return HierarchyInfoVM(parcel)
        }

        override fun newArray(size: Int): Array<HierarchyInfoVM?> {
            return arrayOfNulls(size)
        }
    }
}