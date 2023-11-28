package com.example.proyectofinalapiyugioh.data.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardDBRepository @Inject constructor(private val cardDao:CardDao) {

    val allCard: Flow<List<CardEntity>> = cardDao.getAll()

    @WorkerThread
    suspend fun insert(listCardEntity: List<CardEntity>) {
        cardDao.insert(listCardEntity)
    }
}