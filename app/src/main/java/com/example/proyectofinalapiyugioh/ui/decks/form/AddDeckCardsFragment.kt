package com.example.proyectofinalapiyugioh.ui.decks.form

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyectofinalapiyugioh.R
import com.example.proyectofinalapiyugioh.data.db.DeckCardCrossRef
import com.example.proyectofinalapiyugioh.databinding.FragmentAddDeckBinding
import com.example.proyectofinalapiyugioh.databinding.FragmentAddDeckCardsBinding
import com.google.android.material.textfield.MaterialAutoCompleteTextView
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

        viewLifecycleOwner.lifecycleScope.launch {
            val list = viewModel.getAllName()
            val mutableList: MutableList<String> = mutableListOf()
            list.forEach {
                mutableList.add(it)
            }

            val autoCompleteTextView: AutoCompleteTextView = binding.autoComplete
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, mutableList)
            autoCompleteTextView.setAdapter(adapter)
        }

        binding.addButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    val nameCard = binding.autoComplete.text.toString()
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

