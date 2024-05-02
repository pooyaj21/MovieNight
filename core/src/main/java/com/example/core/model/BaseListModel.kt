package com.example.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class BaseListModel<T>(val list: @RawValue List<T>) : Parcelable