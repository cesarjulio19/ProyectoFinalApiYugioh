package com.example.proyectofinalapiyugioh.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listCardEntity: List<CardEntity>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeckList(listDeckEntity: List<DeckEntity>)

    @Query("SELECT * FROM card")
    fun getAll(): Flow<List<CardEntity>>
    @Query("SELECT * FROM deck")
    fun getAllDeck(): Flow<List<DeckEntity>>

    @Query("SELECT * FROM card WHERE idCard = :id")
    suspend fun getCard(id:Int): CardEntity

    @Transaction
    @Query("SELECT * FROM deck")
    fun getDecksWithCards(): List<DeckWithCards>
}