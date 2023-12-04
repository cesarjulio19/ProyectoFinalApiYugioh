package com.example.proyectofinalapiyugioh.ui.decks.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinalapiyugioh.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class DeckListViewModel @Inject constructor(private val repository: CardRepository): ViewModel() {
    private val _uiState = MutableStateFlow(DeckListUiState(listOf()))

    val uiState: StateFlow<DeckListUiState>
        get() = _uiState.asStateFlow()

    init{
        viewModelScope.launch {
            try{
                repository.refreshList()
            }catch (e:IOException){
                _uiState.value = _uiState.value.copy(errorMessage = e.message!!)
            }
        }

        viewModelScope.launch {
            repository.deck.collect{
                _uiState.value = DeckListUiState(it)
            }
        }
    }
}