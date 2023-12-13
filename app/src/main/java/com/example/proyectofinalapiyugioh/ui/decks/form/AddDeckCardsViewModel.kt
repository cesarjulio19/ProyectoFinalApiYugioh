package com.example.proyectofinalapiyugioh.ui.decks.form

import androidx.lifecycle.ViewModel
import com.example.proyectofinalapiyugioh.data.db.DeckCardCrossRef
import com.example.proyectofinalapiyugioh.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDeckCardsViewModel @Inject constructor(private val repository: CardRepository): ViewModel()
{
    suspend fun getIdCard(name:String): Int{
        val id = repository.getIdCard(name)
        return id
    }

    suspend fun insertDeckCards(deckCards: DeckCardCrossRef){
        repository.insertDeckCards(deckCards)
    }

}