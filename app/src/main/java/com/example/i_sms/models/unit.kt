package com.example.i_sms.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class unit(
    @SerialName("UnitCode")
    val UnitCode: String,

    @SerialName("Unit")
    val Unit: String


)
