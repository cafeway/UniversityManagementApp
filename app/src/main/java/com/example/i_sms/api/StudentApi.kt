package com.example.i_sms.api

import com.example.i_sms.Student
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StudentApi {
    @GET("https://i-sms.herokuapp.com/api/students/show")
    suspend fun getStudents (): Response<List<Student>>

    @POST("https://i-sms.herokuapp.com/api/students")
    suspend fun registerStudent(@Body student: Student): Response<ResponseBody>
}