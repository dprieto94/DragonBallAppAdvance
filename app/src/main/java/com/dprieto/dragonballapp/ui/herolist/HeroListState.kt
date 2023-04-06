package com.dprieto.dragonballapp.ui.herolist

import com.dprieto.dragonballapp.domain.HeroModel

sealed class HeroListState {
    data class Success(val heros: List<HeroModel>) : HeroListState()
    data class Error(val error: String?) : HeroListState()
    data class NetworkError(val code: Int) : HeroListState()
    data class FilterFavorites(val heros: List<HeroModel>) : HeroListState()
}