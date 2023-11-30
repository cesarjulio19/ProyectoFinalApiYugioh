package com.example.proyectofinalapiyugioh.ui.cards.list

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
import com.example.proyectofinalapiyugioh.databinding.FragmentCardListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardListFragment : Fragment() {
    private lateinit var binding: FragmentCardListBinding
    private val viewModel: CardListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardListBinding.inflate(inflater,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CardAdapter(requireContext(), ::onShowDetail)
        val rv = binding.cardList
        rv.adapter = adapter
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    adapter.submitList(it.card)
                }
            }
        }

    }

    private fun onShowDetail(id: Int,view:View) {
        val action = CardListFragmentDirections
            .actionCardListFragmentToCardDetailFragment(id)
        view.findNavController().navigate(action)
    }


}