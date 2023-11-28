package com.example.proyectofinalapiyugioh.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton


interface CardApi {

    @GET("api/v7/cardinfo.php")
    suspend fun fetchCard(@Query("id") id:Int):CardListDetailResponse

    @GET("api/v7/cardinfo.php")
    suspend fun fetchAllCard():CardListResponse
}
@Singleton
class CardService @Inject constructor() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://db.ygoprodeck.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api:CardApi = retrofit.create(CardApi::class.java)
}