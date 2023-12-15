package com.example.proyectofinalapiyugioh.ui.decks.deckCardsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalapiyugioh.data.repository.Card
import com.example.proyectofinalapiyugioh.data.repository.CardRepository
import com.example.proyectofinalapiyugioh.ui.cards.list.CardListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DeckCardsViewModel @Inject constructor(private val repository: CardRepository): ViewModel(){



    suspend fun getDeckCards(id: Int): List<Card>{
        val list = repository.getDecksWithCards(id)
        return list
    }

}