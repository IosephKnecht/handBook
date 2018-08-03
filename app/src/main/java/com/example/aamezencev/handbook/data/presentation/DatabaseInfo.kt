package com.example.aamezencev.handbook.data.presentation

import android.net.Uri

data class DatabaseInfo(var name: String = "Not parsed",
                        var size: String = "Unknown",
                        var uri: Uri = Uri.EMPTY)

