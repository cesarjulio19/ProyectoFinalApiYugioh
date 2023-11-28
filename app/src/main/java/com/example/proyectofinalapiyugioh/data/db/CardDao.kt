package com.example.proyectofinalapiyugioh.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(listCardEntity: List<CardEntity>)
    @Query("SELECT * FROM card")
    fun getAll(): Flow<List<CardEntity>>
}