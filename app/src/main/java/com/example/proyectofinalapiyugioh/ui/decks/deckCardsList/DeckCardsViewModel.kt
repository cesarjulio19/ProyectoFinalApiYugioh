package com.example.proyectofinalapiyugioh.ui.decks.deckCardsList

import androidx.lifecycle.ViewModel
import com.example.proyectofinalapiyugioh.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeckCardsViewModel @Inject constructor(private val repository: CardRepository): ViewModel(){

}