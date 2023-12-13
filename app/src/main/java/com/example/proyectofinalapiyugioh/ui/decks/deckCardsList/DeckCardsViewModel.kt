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

    /*private val _uiState = MutableStateFlow(DeckCardsUiState(listOf()))

    val uiState: StateFlow<DeckCardsUiState>
        get()=_uiState.asStateFlow()

    init {

        viewModelScope.launch {
            try {
                repository.refreshList()

            } catch (e: IOException) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message!!)
            }
        }

        viewModelScope.launch{
            getDeckCards()
        }
    }*/

    suspend fun getDeckCards(id: Int): List<Card>{
        val list = repository.getDecksWithCards(id)
        return list
    }

}