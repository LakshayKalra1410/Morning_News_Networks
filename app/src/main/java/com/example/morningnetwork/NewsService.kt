package com.example.morningnetwork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object NewsService {
    private val retrofit=Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val newsInstance:NewsInterface= retrofit.create(NewsInterface::class.java)

}