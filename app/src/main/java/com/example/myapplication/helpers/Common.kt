package com.example.myapplication.helpers

object Common {
    private val BASE_URL = "http://dummy.restapiexample.com/api/v1/"

    val retrofitService: JsonPlaceHolderApi
        get() = RetrofitClient.getClient(BASE_URL).create(JsonPlaceHolderApi::class.java)
}