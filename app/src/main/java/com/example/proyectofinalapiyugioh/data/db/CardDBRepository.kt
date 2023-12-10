package com.example.proyectofinalapiyugioh.data.db

import androidx.annotation.WorkerThread
import com.example.proyectofinalapiyugioh.data.repository.Card
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardDBRepository @Inject constructor(private val cardDao:CardDao) {

    val allCard: Flow<List<CardEntity>> = cardDao.getAll()
    val allDeck: Flow<List<DeckEntity>> = cardDao.getAllDeck()

    suspend fun getCardById(id:Int):CardEntity{
        val cardD = cardDao.getCard(id)
        return cardD
    }

    @WorkerThread
    suspend fun insert(listCardEntity: List<CardEntity>) {
        cardDao.insert(listCardEntity)
    }

    @WorkerThread
    suspend fun insertDeck(deck: DeckEntity){
        cardDao.insertDeck(deck)
    }
}