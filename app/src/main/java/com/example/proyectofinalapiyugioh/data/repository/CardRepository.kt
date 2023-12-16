package com.example.proyectofinalapiyugioh.data.repository

import com.example.proyectofinalapiyugioh.data.api.CardApiRepository
import com.example.proyectofinalapiyugioh.data.api.asEntityModel
import com.example.proyectofinalapiyugioh.data.db.CardDBRepository
import com.example.proyectofinalapiyugioh.data.db.DeckCardCrossRef
import com.example.proyectofinalapiyugioh.data.db.DeckEntity
import com.example.proyectofinalapiyugioh.data.db.asCard
import com.example.proyectofinalapiyugioh.data.db.asCardD
import com.example.proyectofinalapiyugioh.data.db.asDeck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CardRepository @Inject constructor(
    private val dbRepository: CardDBRepository,
    private val apiRepository: CardApiRepository
) {
    val card: Flow<List<Card>>
        get() {

            val list = dbRepository.allCard.map {
                it.asCard()
            }
            return list


        }

    val deck: Flow<List<Deck>>
        get() {
            val list = dbRepository.allDeck.map {
                it.asDeck()
            }
            return list
        }



     suspend fun cardDetail(id:Int) = withContext(Dispatchers.IO){

        dbRepository.getCardById(id).asCardD()


    }

    suspend fun getIdCard(name:String) = withContext(Dispatchers.IO) {
        dbRepository.getIdCard(name)
    }

    suspend fun getDecksWithCards(id:Int) = withContext(Dispatchers.IO) {
        dbRepository.getDecksWithCards(id).cards.asCard()
    }

    suspend fun getAllName() =  withContext(Dispatchers.IO) {
        dbRepository.getAllName()
    }


    suspend fun refreshList() = withContext(Dispatchers.IO){
        val apiCard = apiRepository.getAll()
        dbRepository.insert(apiCard.asEntityModel())
    }

    suspend fun insertDeck(deck: DeckEntity) = withContext(Dispatchers.IO){
        dbRepository.insertDeck(deck)
    }

    suspend fun insertDeckCards(deckCards: DeckCardCrossRef) = withContext(Dispatchers.IO){
        dbRepository.insertDeckCards(deckCards)
    }
}