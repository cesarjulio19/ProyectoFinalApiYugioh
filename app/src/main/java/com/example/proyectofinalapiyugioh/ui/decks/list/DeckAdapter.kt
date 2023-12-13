package com.example.proyectofinalapiyugioh.ui.decks.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectofinalapiyugioh.data.repository.Card
import com.example.proyectofinalapiyugioh.data.repository.Deck
import com.example.proyectofinalapiyugioh.databinding.DeckItemBinding
import com.example.proyectofinalapiyugioh.ui.cards.list.CardAdapter

class DeckAdapter(private val onShowCards:(id:Int,name:String,v: View)->Unit)
    : RecyclerView.Adapter<DeckAdapter.DeckItemViewHolder>(){

    private var deckList: List<Deck> = emptyList()

    inner class DeckItemViewHolder(private val binding: DeckItemBinding)
        :RecyclerView.ViewHolder(binding.root){

            fun bind(d: Deck){
                binding.nameText.text = d.name

                binding.deck.setOnClickListener {
                    onShowCards(d.id,d.name, binding.root)
                }
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeckItemViewHolder {
        val binding = DeckItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)

        return DeckItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeckItemViewHolder, position: Int) {
        val deck = deckList[position]
        holder.bind(deck)
    }

    override fun getItemCount(): Int {
        return deckList.size
    }

    fun submitList(list: List<Deck>){
        deckList = list
        notifyDataSetChanged()
    }
}