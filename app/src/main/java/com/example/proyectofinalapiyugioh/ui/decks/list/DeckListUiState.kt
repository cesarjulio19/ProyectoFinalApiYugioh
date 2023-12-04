package com.example.proyectofinalapiyugioh.ui.decks.list

import com.example.proyectofinalapiyugioh.data.repository.Deck

data class DeckListUiState(
    val deck: List<Deck>,
    val errorMessage:String?=null
)
