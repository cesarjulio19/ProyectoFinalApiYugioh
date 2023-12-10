package com.example.proyectofinalapiyugioh.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CardEntity::class, DeckEntity::class, DeckCardCrossRef::class], version = 1)
abstract class CardDatabase(): RoomDatabase() {

    abstract fun CardDao(): CardDao

    companion object{
        @Volatile
        private var INSTANCE: CardDatabase? = null

        fun getInstance(context: Context): CardDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): CardDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                CardDatabase::class.java,
                "card_db"
            ).build()
        }

    }

}