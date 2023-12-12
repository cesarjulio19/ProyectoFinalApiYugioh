package com.example.proyectofinalapiyugioh.ui.decks.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import com.example.proyectofinalapiyugioh.R
import com.example.proyectofinalapiyugioh.databinding.FragmentCardListBinding
import com.example.proyectofinalapiyugioh.databinding.FragmentDeckListBinding
import com.example.proyectofinalapiyugioh.ui.cards.list.CardListFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DeckListFragment : Fragment() {
    private lateinit var binding: FragmentDeckListBinding
    private val viewModel: DeckListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeckListBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = DeckAdapter(::onShowCards)
        val rv = binding.deckList
        rv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.uiState.collect{
                    adapter.submitList(it.deck)
                }
            }
        }

        binding.addDeck.setOnClickListener {

            val action = DeckListFragmentDirections
                .actionDeckListFragmentToAddDeckFragment()
            view.findNavController().navigate(action)
            
        }


    }

    private fun onShowCards(id: Int,view:View) {
        val action = DeckListFragmentDirections
            .actionDeckListFragmentToDeckCardsFragment(id)
        view.findNavController().navigate(action)
    }
}