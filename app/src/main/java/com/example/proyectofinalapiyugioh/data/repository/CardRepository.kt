package com.example.proyectofinalapiyugioh.data.repository

import com.example.proyectofinalapiyugioh.data.api.CardApiRepository
import com.example.proyectofinalapiyugioh.data.api.asEntityModel
import com.example.proyectofinalapiyugioh.data.db.CardDBRepository
import com.example.proyectofinalapiyugioh.data.db.asCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepository @Inject constructor(
    private val dbRepository: CardDBRepository,
    private val apiRepository: CardApiRepository
) {
    val card: Flow<List<Card>>
        get() {

            val list = dbRepository.allCard.map {
                it.asCard()
            }
            return list
        }

    suspend fun refreshList() = withContext(Dispatchers.IO){
        val apiCard = apiRepository.getAll()
        dbRepository.insert(apiCard.asEntityModel())
    }
}