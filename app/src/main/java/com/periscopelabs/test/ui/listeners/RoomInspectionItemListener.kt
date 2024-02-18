package com.periscopelabs.test.ui.listeners

import com.periscopelabs.test.models.InspectionsRoomModel

interface RoomInspectionItemListener {
    fun onStatusButtonClick(position: Int, status: Int)
}