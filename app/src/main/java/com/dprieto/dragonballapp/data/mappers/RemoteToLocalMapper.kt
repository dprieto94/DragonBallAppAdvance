package com.dprieto.dragonballapp.data.mappers

import com.dprieto.dragonballapp.data.local.model.HeroRoomModel
import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import javax.inject.Inject

class RemoteToLocalMapper @Inject constructor() {

    fun map(heroResponseList: List<HeroResponse>): List<HeroRoomModel>{
        return heroResponseList.map { map(it) }
    }

    fun map(heroResponse: HeroResponse): HeroRoomModel {
        return HeroRoomModel(heroResponse.id, heroResponse.name, heroResponse.photo, heroResponse.description, heroResponse.favorite)
    }

}