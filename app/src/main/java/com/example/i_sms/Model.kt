package com.example.i_sms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Model(
    @SerialName("Email")
    val Email: String,

    @SerialName("password")
    val Password: String
)
