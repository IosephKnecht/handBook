package com.example.aamezencev.handbook.domain.mappers

import com.example.aamezencev.handbook.data.presentation.LogItemModel
import com.hypertrack.hyperlog.DeviceLogModel

object LogMapper {
    fun fromPresentation(logList: List<DeviceLogModel>): List<LogItemModel> {
        return logList.map { LogItemModel(it.id.toLong(), it.deviceLog) }
    }
}