package com.example.proyectofinalapiyugioh.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.proyectofinalapiyugioh.data.repository.Card


@Entity(tableName = "card")
data class CardEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val desc: String,
    val archetype: String,
    val imageUrl: String,
    val imageUrlSmall: String,
    val favorite:Boolean=false
)

fun List<CardEntity>.asCard():List<Card> {
    return this.map {
        Card(
            it.id,
            it.name,
            it.type,
            it.desc,
            it.archetype,
            it.imageUrl,
            it.imageUrlSmall,
            it.favorite
        )
    }
}

