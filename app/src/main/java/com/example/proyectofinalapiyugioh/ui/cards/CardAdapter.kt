package com.example.proyectofinalapiyugioh.ui.cards

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.imageLoader
import coil.request.ImageRequest
import com.example.proyectofinalapiyugioh.data.repository.Card
import com.example.proyectofinalapiyugioh.databinding.CardItemBinding

class CardAdapter(private val context: Context)
    : RecyclerView.Adapter<CardAdapter.CardItemViewHolder>() {

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

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardItemViewHolder {
        val binding = CardItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CardItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardItemViewHolder, position: Int) {
        val card = cardList[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int {
        return cardList.size
    }

    fun submitList(list: List<Card>) {
        cardList = list
        notifyDataSetChanged()
    }
}