package com.example.aamezencev.handbook.presentation.screen.viewModel

import android.os.Parcel
import android.os.Parcelable
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.data.presentation.ThreeDimensionalModel
import com.example.aamezencev.handbook.presentation.screen.HierarchyScreenContract

class HierarchyInfoVM() : AbstractViewModel(), HierarchyScreenContract.ViewModel,Parcelable {
    override var description: String? = null
        get() = field
        set(value) {
            field = value
            notifyChange()
        }
    override var listModels: List<ThreeDimensionalModel>? = null



    constructor(parcel: Parcel) : this() {

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {

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