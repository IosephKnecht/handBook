package com.example.aamezencev.handbook.data.presentation

import android.net.Uri

data class DatabaseInfo(var name: String = "Not parsed",
                        var size: Long = -1,
                        var uri: Uri = Uri.EMPTY)

