package com.example.proyectofinalapiyugioh.ui.decks.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.proyectofinalapiyugioh.R
import com.example.proyectofinalapiyugioh.data.db.DeckEntity
import com.example.proyectofinalapiyugioh.data.repository.CardRepository
import com.example.proyectofinalapiyugioh.databinding.FragmentAddDeckBinding
import com.example.proyectofinalapiyugioh.ui.cards.detail.CardDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddDeckFragment : Fragment() {
    private lateinit var binding: FragmentAddDeckBinding
    private val viewModel: AddDeckViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddDeckBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.topToolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {

            findNavController().navigateUp()
        }

        binding.addButton.setOnClickListener {


            viewLifecycleOwner.lifecycleScope.launch{
                val name = binding.titleInput.text.toString()
                val deck = DeckEntity(nameDeck = name)

                viewModel.insertDeck(deck)

                findNavController().navigateUp()
            }
        }
    }



}