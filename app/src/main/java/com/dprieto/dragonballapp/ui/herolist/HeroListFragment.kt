package com.dprieto.dragonballapp.ui.herolist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dprieto.dragonballapp.databinding.FragmentHeroListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroListFragment : Fragment() {

    private var _binding: FragmentHeroListBinding? = null

    private val binding get() = _binding!!

    private val viewModel: HeroListViewModel by viewModels()

    private val adapter = HeroListAdapter {
        //Navigation to new detail fragment
        findNavController().navigate(HeroListFragmentDirections.actionHerosListFragmentToDetailHeroFragment(it.name))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHeroListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding){
            herosList.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            herosList.adapter = adapter


            viewModel.state.observe(viewLifecycleOwner){state->

                when(state){
                    is HeroListState.Error -> {
                        Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
                    }
                    is HeroListState.NetworkError -> {
                        Toast.makeText(requireContext(), "Network error with code ${state.code}", Toast.LENGTH_LONG).show()
                    }
                    is HeroListState.Success -> {
                        adapter.submitList( state.heros )

                        buttonFavorite.setOnClickListener {
                            viewModel.filterFavorite(state.heros)
                        }

                    }
                    is HeroListState.FilterFavorites -> {
                        adapter.submitList( state.heros.filter {
                            it.favorite
                        } )
                        buttonFavorite.setOnClickListener {
                            viewModel.unFilterFavorite(state.heros)
                        }
                    }
                }
            }
            viewModel.getHeroList()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}