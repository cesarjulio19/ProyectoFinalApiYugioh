package com.example.proyectofinalapiyugioh.ui.decks.deckCardsList

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyectofinalapiyugioh.R
import com.example.proyectofinalapiyugioh.databinding.FragmentCardListBinding
import com.example.proyectofinalapiyugioh.databinding.FragmentDeckCardsBinding
import com.example.proyectofinalapiyugioh.databinding.FragmentDeckListBinding
import com.example.proyectofinalapiyugioh.ui.cards.list.CardAdapter
import com.example.proyectofinalapiyugioh.ui.decks.list.DeckListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeckCardsFragment : Fragment() {
    private lateinit var binding: FragmentDeckCardsBinding
    private val viewModel: DeckCardsViewModel by viewModels()
    private val args: DeckCardsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeckCardsBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = binding.topAppBar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {

            findNavController().navigateUp()
        }
        binding.topAppBar.title = args.name

        binding.addCard.setOnClickListener {
            val action = DeckCardsFragmentDirections
                .actionDeckCardsFragmentToAddDeckCardsFragment(args.id)
            view.findNavController().navigate(action)
        }

        val adapter = DeckCardsAdapter(requireContext(), ::onShowDetail)
        val rv = binding.cardList
        rv.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch{
            adapter.submitList(viewModel.getDeckCards(args.id))
        }

        binding.shareDeck.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch{
                val deck = viewModel.getDeckCards(args.id)
                var deckString = ""
                deck.forEach(){
                    deckString = deckString + "[ "+ it.name + " ] "
                }
                val shareText = args.name + ": " + deckString

                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,shareText)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(intent,null)
                startActivity(shareIntent)

            }




        }
    }

    private fun onShowDetail(id: Int,view:View) {
        val action = DeckCardsFragmentDirections
            .actionDeckCardsFragmentToCardDetailFragment2(id)
        view.findNavController().navigate(action)
    }

}