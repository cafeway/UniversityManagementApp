package com.example.i_sms.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnitDetails(
    @SerialName("units")
    val unit: ArrayList<unit>
)
