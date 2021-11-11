package com.example.domain.entities

import com.google.android.gms.maps.model.LatLng

data class PbDevice(
    val macAddress: String,
    val guid: String,
    val deviceName: String,
    val latlng: LatLng,
    val timeStamp: Long,
    val imageUrl: String
)
