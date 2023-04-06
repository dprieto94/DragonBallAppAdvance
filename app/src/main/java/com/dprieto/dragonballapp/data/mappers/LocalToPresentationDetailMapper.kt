package com.dprieto.dragonballapp.data.mappers

import com.dprieto.dragonballapp.data.local.model.HeroRoomModel
import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import com.dprieto.dragonballapp.domain.HeroModelDetail
import javax.inject.Inject

class LocalToPresentationDetailMapper @Inject constructor(){

    fun map(hero: HeroRoomModel): HeroModelDetail {
        return HeroModelDetail(hero.id, hero.name, hero.photo, hero.descripcion, hero.favorite)
    }

}