package com.dprieto.dragonballapp.ui.detail

import com.dprieto.dragonballapp.domain.HeroModelDetail

sealed class HeroDetailState {
    data class Success(val hero: HeroModelDetail) : HeroDetailState()
    data class Error(val error: String?) : HeroDetailState()
    data class NetworkError(val code: Int) : HeroDetailState()
}