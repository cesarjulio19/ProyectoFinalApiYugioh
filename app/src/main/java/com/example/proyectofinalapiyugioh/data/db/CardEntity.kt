package com.example.proyectofinalapiyugioh.data.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.proyectofinalapiyugioh.data.repository.Card
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


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
    val level: Int,
    val atk: Int,
    val def: Int
)

@Entity(tableName = "deck")
data class DeckEntity(
    @PrimaryKey
    val idDeck: Int,
    val nameDeck: String
)

@Entity(primaryKeys = ["idDeck", "idCard"])
data class DeckCardCrossRef(
    val idDeck: Int,
    val idCard: Int
)

/*data class DeckWithCards(
    @Embedded val deck: DeckEntity,
    @Relation()
)*/

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
            it.level,
            it.atk,
            it.def
        )
    }
}

fun CardEntity.asCardD(): Card {
    return Card(id,
            name,
            type,
            desc,
            archetype,
            imageUrl,
            imageUrlSmall,
            level,
            atk,
            def)



}




