package com.example.i_sms.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class GetResponse(
    @SerialName("timetable")
    val timetable: ArrayList<Timetable>
)
