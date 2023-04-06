package com.dprieto.dragonballapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import coil.load
import com.dprieto.dragonballapp.R
import com.dprieto.dragonballapp.databinding.FragmentHeroDetailBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroDetailFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentHeroDetailBinding? = null

    private val binding get() = _binding!!

    private val args : HeroDetailFragmentArgs by navArgs()
    private val viewModel: HeroDetailViewModel by viewModels()

    private lateinit var googleMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHeroDetailBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)

        binding.heroFavoriteDetail.setOnClickListener {
            viewModel.updateFavorite(args.hero.id, args.hero.name)
        }

        viewModel.state.observe(viewLifecycleOwner){ state ->
            when(state){
                is HeroDetailState.SuccessDetail -> {
                    binding.heroNameDetail.text = state.hero.name
                    binding.heroImageDetail.load(state.hero.photo)
                    binding.heroDescriptionDetail.text = state.hero.description
                    binding.heroFavoriteDetail.isChecked = state.hero.favorite
                }
                is HeroDetailState.Error -> {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
                }
                is HeroDetailState.NetworkError -> {
                    Toast.makeText(requireContext(), "Network error with code ${state.code}", Toast.LENGTH_LONG).show()
                }
            }
        }

        viewModel.getSuperHeroDetail(args.hero.name)
        viewModel.getLocations(args.hero.id)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        viewModel.locations.observe(viewLifecycleOwner){ state ->
            when(state){
                is HeroDetailLocationsState.Success -> {
                    state.locations.forEach { location ->
                        addMarkerToMap(location.id, location.latitud.toDouble(), location.longitud.toDouble())
                    }
                }
                is HeroDetailLocationsState.Error -> {
                    Toast.makeText(requireContext(), state.error, Toast.LENGTH_LONG).show()
                }
                is HeroDetailLocationsState.NetworkError -> {
                    Toast.makeText(requireContext(), "Network error with code ${state.code}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun addMarkerToMap(name: String, latitude: Double, longitude: Double){

        val marker = LatLng(latitude, longitude)
        googleMap.addMarker(
            MarkerOptions()
                .position(marker)
                .title(name)
        )
        }


}