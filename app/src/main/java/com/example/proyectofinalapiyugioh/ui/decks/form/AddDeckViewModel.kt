package com.example.proyectofinalapiyugioh.ui.decks.form

import androidx.lifecycle.ViewModel
import com.example.proyectofinalapiyugioh.data.db.DeckEntity
import com.example.proyectofinalapiyugioh.data.repository.Card
import com.example.proyectofinalapiyugioh.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDeckViewModel @Inject constructor(private val repository: CardRepository): ViewModel(){


    suspend fun insertDeck(deck:DeckEntity) {

        repository.insertDeck(deck)
    }
}