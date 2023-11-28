package com.example.proyectofinalapiyugioh.ui.cards

import com.example.proyectofinalapiyugioh.data.repository.Card

data class CardListUiState(
  val card:List<Card>,
  val errorMessage:String?=null
)
