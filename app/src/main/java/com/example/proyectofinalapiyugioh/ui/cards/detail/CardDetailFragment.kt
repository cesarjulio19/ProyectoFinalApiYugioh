package com.example.proyectofinalapiyugioh.ui.cards.detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.proyectofinalapiyugioh.R
import com.example.proyectofinalapiyugioh.databinding.FragmentCardDetailBinding
import com.example.proyectofinalapiyugioh.databinding.FragmentCardListBinding
import com.example.proyectofinalapiyugioh.ui.cards.list.CardListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardDetailFragment : Fragment() {
    private lateinit var binding: FragmentCardDetailBinding
    private val viewModel: CardDetailViewModel by viewModels()
    private val args: CardDetailFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardDetailBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = binding.topAppBar
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        toolbar.setNavigationOnClickListener {

            findNavController().navigateUp()
        }

        viewLifecycleOwner.lifecycleScope.launch{
            val cardD = viewModel.getCard(args.id)
            binding.nameText.text = cardD.level.toString() +
                    "*" +
                    " " +
                    cardD.atk.toString() +
                    "\\" + cardD.def.toString()

            binding.typeText.text = cardD.type
            binding.descText.text = cardD.desc
            binding.archetypeText.text = cardD.archetype
            binding.topAppBar.title = cardD.name

            binding.image.load(cardD.imageUrl) {

                placeholder(R.drawable.ic_launcher_background)
            }

        }




    }

}