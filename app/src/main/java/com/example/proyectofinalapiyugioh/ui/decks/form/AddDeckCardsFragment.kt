package com.example.proyectofinalapiyugioh.ui.decks.form

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyectofinalapiyugioh.R
import com.example.proyectofinalapiyugioh.data.db.DeckCardCrossRef
import com.example.proyectofinalapiyugioh.databinding.FragmentAddDeckBinding
import com.example.proyectofinalapiyugioh.databinding.FragmentAddDeckCardsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AddDeckCardsFragment : Fragment() {
    private lateinit var binding: FragmentAddDeckCardsBinding
    private val viewModel: AddDeckCardsViewModel by viewModels()
    private val args: AddDeckCardsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddDeckCardsBinding.inflate(inflater,
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
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    val nameCard = binding.titleInput.text.toString()
                    Log.d("NameCard", "$nameCard")
                    val idCard = viewModel.getIdCard(nameCard)
                    Log.d("IdCard", "$idCard")
                    val idDeck = args.id
                    Log.d("IdDeck", "$idDeck")
                    val deckCards = DeckCardCrossRef(idDeck, idCard)
                    viewModel.insertDeckCards(deckCards)

                    findNavController().navigateUp()

                }catch (e: Exception){
                    Log.e("API_ERROR_Insert", "Error insert: $e")
                }
            }

            }
        }
    }

