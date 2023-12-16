package com.example.proyectofinalapiyugioh.data.api

import com.example.proyectofinalapiyugioh.data.db.CardEntity

data class CardApiModel(val id: Int,
                        val name: String,
                        val type: String,
                        val desc: String,
                        val archetype: String,
                        val imageUrl: String,
                        val imageUrlSmall: String,
                        val level: Int,
                        val atk: Int,
                        val def: Int)

data class CardListResponse(
    val data: List<CardListItemResponse>
)

data class CardListItemResponse(
    val id:Int,
    val name:String
)

data class CardListDetailResponse(val data: List<CardDetailResponse>)

data class CardDetailResponse(val id: Int,
                              val name: String,
                              val type: String,
                              val desc: String,
                              val archetype: String,
                              val card_images: List<CardImage>,
                              val level: Int,
                              val atk: Int,
                              val def: Int){
    fun asApiModel():CardApiModel {
        return CardApiModel(
            id,
            name,
            type,
            desc,
            archetype,
            card_images[0].image_url_cropped,
            card_images[0].image_url_small,
            level,
            atk,
            def
        )
    }
}

data class CardImage(
    val image_url_cropped: String,
    val image_url_small: String,

    )

fun List<CardApiModel>.asEntityModel(): List<CardEntity> {
    return this.map {
        CardEntity(
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
