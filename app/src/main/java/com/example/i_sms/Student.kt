package com.example.i_sms

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Student(
    @SerialName("RegNo")
    var RegNo: String,

    @SerialName("FirstName")
    var FirstName: String,

    @SerialName("LastName")
    var SecondName: String,

    @SerialName("LastName")
    var LastName: String,

    @SerialName("IdNo")
    var IdNo: String,

    @SerialName("PhoneNumber")
    var PhoneNumber: String,

    @SerialName("School")
    var School: String,

    @SerialName("Course")
    var Course: String,

    @SerialName("FathersPhone")
    var FathersPhone: String,

    @SerialName("MothersPhone")
    var MothersPhone: String,

    @SerialName("Mothersname")
    var MothersName: String,

    @SerialName("Fathersname")
    var FathersName: String,

    @SerialName("gender")
    var Gender: String,

)
