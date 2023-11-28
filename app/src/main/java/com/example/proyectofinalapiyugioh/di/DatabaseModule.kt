package com.example.proyectofinalapiyugioh.di

import android.content.Context
import com.example.proyectofinalapiyugioh.data.db.CardDao
import com.example.proyectofinalapiyugioh.data.db.CardDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCardDatabase(@ApplicationContext context: Context): CardDatabase {
        return CardDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideCardDao(database: CardDatabase): CardDao {
        return database.CardDao()
    }

}