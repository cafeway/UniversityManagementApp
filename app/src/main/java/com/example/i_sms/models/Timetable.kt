package com.example.i_sms.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Timetable(
    @SerialName("TimeOfDay")
    val TimeOfDay: String,

    @SerialName("Day")
    val Day: String,

    @SerialName("UnitCode")
    val UnitCode: String,

    @SerialName("Venue")
    val Venue: String,

    @SerialName("Lecturer")
    val Lecturer: String,

    @SerialName("Year")
    val Year: String,

    @SerialName("ClassCode")
    val ClassCode: String,

    @SerialName("TotalClasses")
    val TotalClasses: Int,

    @SerialName("RemainingClasses")
    val RemainingClasses: Int,
)
