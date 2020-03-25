package com.example.myapplication.helpers

import com.example.myapplication.model.Data
import com.example.myapplication.model.EmployeeM
import com.example.myapplication.model.User_Model
import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("employees")
    fun getEmployeeLists(): Call<EmployeeM>

}