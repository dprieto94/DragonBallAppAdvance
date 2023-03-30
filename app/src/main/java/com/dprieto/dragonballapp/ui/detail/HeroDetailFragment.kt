package com.dprieto.dragonballapp.ui.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.dprieto.dragonballapp.databinding.FragmentHeroDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDetailFragment : Fragment() {

    private var _binding: FragmentHeroDetailBinding? = null

    private val binding get() = _binding!!

    private val args : HeroDetailFragmentArgs by navArgs()
    private val viewModel: HeroDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner){
            when(it){
                is HeroDetailState.Success -> {
                    binding.heroNameDetail.text = it.hero.name
                    binding.heroImageDetail.load(it.hero.photo)
                    binding.heroDescriptionDetail.text = it.hero.description
                    binding.heroFavoriteDetail.isChecked = it.hero.favorite
                }
                else -> {

                }
            }
        }

        viewModel.getSuperHeroDetail(args.superheroName)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}