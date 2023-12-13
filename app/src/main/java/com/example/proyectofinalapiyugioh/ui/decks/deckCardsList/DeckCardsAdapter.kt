package com.example.proyectofinalapiyugioh.ui.decks.deckCardsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.example.proyectofinalapiyugioh.data.repository.Card
import com.example.proyectofinalapiyugioh.databinding.CardItemBinding
import com.example.proyectofinalapiyugioh.ui.cards.list.CardAdapter

class DeckCardsAdapter(private val context: Context, private val onShowDetail:(id:Int,v: View)->Unit)
    : RecyclerView.Adapter<DeckCardsAdapter.CardItemViewHolder>(){

    private var cardList: List<Card> = emptyList()

    inner class CardItemViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(c: Card) {
            binding.nameText.text = c.name

            val imageRequest = ImageRequest.Builder(context)
                .data(c.imageUrlSmall)
                .crossfade(true)
                .target(binding.image)
                .build()

            context.imageLoader.enqueue(imageRequest)

            binding.card.setOnClickListener {
                onShowDetail(c.id,binding.root)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
    : DeckCardsAdapter.CardItemViewHolder {
        val binding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    override fun onBindViewHolder(holder: CardItemViewHolder, position: Int) {
        val card = cardList[position]
        holder.bind(card)
    }

    fun submitList(list: List<Card>) {
        cardList = list
        notifyDataSetChanged()
    }


}