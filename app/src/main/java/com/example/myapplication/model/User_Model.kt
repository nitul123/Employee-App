package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

class User_Model (

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("username")
    val username:String
)