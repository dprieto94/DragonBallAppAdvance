package com.dprieto.dragonballapp.ui.detail

import com.dprieto.dragonballapp.data.remote.response.LocationsResponse


sealed class HeroDetailLocationsState {
    data class Success(val locations: List<LocationsResponse>) : HeroDetailLocationsState()
    data class Error(val error: String?) : HeroDetailLocationsState()
    data class NetworkError(val code: Int) : HeroDetailLocationsState()
}