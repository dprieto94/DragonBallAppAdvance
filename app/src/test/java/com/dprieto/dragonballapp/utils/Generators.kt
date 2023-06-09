package com.dprieto.dragonballapp.utils

import com.dprieto.dragonballapp.data.local.model.HeroRoomModel
import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import com.dprieto.dragonballapp.data.remote.response.LocationsResponse
import com.dprieto.dragonballapp.domain.HeroModel
import com.dprieto.dragonballapp.domain.HeroModelDetail
import com.dprieto.dragonballapp.ui.detail.HeroDetailLocationsState
import com.dprieto.dragonballapp.ui.detail.HeroDetailState
import com.dprieto.dragonballapp.ui.herolist.HeroListState
import com.dprieto.dragonballapp.ui.login.LoginState


fun generateToken(): String{
    return "awiouwabodawoiuwy9e81760461hd108h983gf7926g"
}

fun generateHerosRemote(): List<HeroResponse> {
    return (0 until 10).map {
        HeroResponse(
            "ID: $it",
            "Name $it",
            "Photo $it",
            "Description $it",
            it%2 == 0
        )
    }
}

fun generateLocations(): List<LocationsResponse>{
    return (0 until 10).map {
        LocationsResponse(
            "ID: $it",
            "DateShow $it",
            "Latitud $it",
            "Longitud $it"
        )
    }
}

fun generateHerosLocal(): List<HeroRoomModel> {
    return (0 until 10).map {
        HeroRoomModel(
            "ID: $it",
            "Name $it",
            "Photo $it",
            "Description $it",
            false
        )
    }
}

fun generateHerosPresentation(): List<HeroModel> {
    return (0 until 10).map {
        HeroModel(
            "ID: $it",
            "Name $it",
            "Photo $it",
            false
        )
    }
}

fun generateLoginState(): LoginState{
    return LoginState.Success(generateToken())
}

fun generateHeroListState(): HeroListState{
    return HeroListState.Success(generateHerosPresentation())
}

fun generateHeroDetailState(): HeroDetailState{
    return HeroDetailState.SuccessDetail(HeroModelDetail("ID", "NAME", "PHOTO", "DESC", true))
}

fun generateLocationsState(): HeroDetailLocationsState{
    return HeroDetailLocationsState.Success(generateLocations())
}
