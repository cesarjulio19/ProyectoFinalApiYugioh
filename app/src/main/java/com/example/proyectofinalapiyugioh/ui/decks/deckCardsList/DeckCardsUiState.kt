package com.example.proyectofinalapiyugioh.ui.decks.deckCardsList

import com.example.proyectofinalapiyugioh.data.repository.Card

data class DeckCardsUiState(val card:List<Card>,
                            val errorMessage:String?=null)
