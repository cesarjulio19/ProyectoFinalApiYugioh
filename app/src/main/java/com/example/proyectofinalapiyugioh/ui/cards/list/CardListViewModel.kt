package com.example.proyectofinalapiyugioh.ui.cards.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class CardListViewModel @Inject constructor(private val repository: CardRepository): ViewModel() {

    private val _uiState = MutableStateFlow(CardListUiState(listOf()))

    val uiState: StateFlow<CardListUiState>
        get()=_uiState.asStateFlow()

    init{

        viewModelScope.launch {
            try {
                repository.refreshList()

            }catch (e:IOException){
                _uiState.value = _uiState.value.copy(errorMessage = e.message!!)
            }
        }

        viewModelScope.launch {
            repository.card.collect {
                _uiState.value = CardListUiState(it)
            }
        }

    }



}