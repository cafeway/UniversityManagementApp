package com.example.i_sms.api

import com.example.i_sms.models.GetResponse
import com.example.i_sms.models.Student
import com.example.i_sms.models.Timetable
import com.example.i_sms.models.UnitDetails
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface StudentApi {
    @GET("https://i-sms.herokuapp.com/api/students/show")
    suspend fun getStudents (): Response<List<Student>>

    @POST("http://10.0.2.2:8000/api/students")
    suspend fun registerStudent(@Body student: Student): Response<ResponseBody>

    @GET("http://10.0.2.2:8000/api/getTimetable")
    fun getTimeTable():Call<GetResponse>

    @GET("http://10.0.2.2:8000/api/getUnits")
    fun getUnits():Call<UnitDetails>
}