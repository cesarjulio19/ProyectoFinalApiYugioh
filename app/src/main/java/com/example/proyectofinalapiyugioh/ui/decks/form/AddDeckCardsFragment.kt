package com.example.proyectofinalapiyugioh.ui.decks.form

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyectofinalapiyugioh.R
import com.example.proyectofinalapiyugioh.databinding.FragmentAddDeckBinding
import com.example.proyectofinalapiyugioh.databinding.FragmentAddDeckCardsBinding
import dagger.hilt.android.AndroidEntryPoint

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
    }

}