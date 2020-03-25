package com.example.myapplication.model


import com.google.gson.annotations.SerializedName

data class EmployeeM(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("status")
    val status: String
)