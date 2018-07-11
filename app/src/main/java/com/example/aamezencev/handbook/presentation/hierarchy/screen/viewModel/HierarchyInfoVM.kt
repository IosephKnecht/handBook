package com.example.aamezencev.handbook.presentation.hierarchy.screen.viewModel

import android.databinding.Bindable
import android.os.Parcel
import android.os.Parcelable
import android.text.SpannableStringBuilder
import com.example.aamezencev.handbook.common.viewModel.AbstractViewModel
import com.example.aamezencev.handbook.presentation.hierarchy.screen.HierarchyScreenContract
import com.example.aamezencev.handbook.BR

class HierarchyInfoVM() : AbstractViewModel(), HierarchyScreenContract.ViewModel,Parcelable {
    override var description = SpannableStringBuilder()
        @Bindable get() = field
        set(value) {
            field = value
            notifyPropertyChanged(BR.viewModel)
            notifyChange()
        }

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