package com.example.i_sms.api



import com.example.i_sms.models.Model
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface Auth {
    @POST("https://i-sms.herokuapp.com/api/login")
    suspend fun Login(@Body model: Model): Response<ResponseBody>
}