package com.example.proyectofinalapiyugioh.ui.cards.detail

import com.example.proyectofinalapiyugioh.data.repository.Card

data class CardDetailUiState(
    val card: List<Card>,
    val errorMessage:String?=null
 )
