package com.example.proyectofinalapiyugioh.data.api

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardApiRepository @Inject constructor(private val service:CardService){

    suspend fun getAll():List<CardApiModel>{
        val simpleList = service.api.fetchAllCard()
        val list: MutableList<CardApiModel> = mutableListOf()
        simpleList.data.take(50).forEach() {

            try {


                val fetchCardUrl = "https://db.ygoprodeck.com/api/v7/cardinfo.php/${it.id}"
                Log.d("FETCH_URL", "URL for ${it.id}: $fetchCardUrl")
                val detailListResponse: CardListDetailResponse = service.api.fetchCard(it.id)
                Log.d("CARD_API_URL", "URL for ${it.id}: ${service.api.fetchCard(it.id)}")

                val cardApiModel = CardApiModel(
                    detailListResponse.data[0].id,
                    detailListResponse.data[0].name,
                    detailListResponse.data[0].type,
                    detailListResponse.data[0].desc,
                    detailListResponse.data[0].archetype?: "No Archetype",
                    detailListResponse.data[0].card_images[0].image_url,
                    detailListResponse.data[0].card_images[0].image_url_small
                )
                list.add(cardApiModel)
            } catch (e: Exception) {
                Log.e("API_ERROR", "Error fetching card ${it.name}: $e")
            }
        }
        return list
    }
}