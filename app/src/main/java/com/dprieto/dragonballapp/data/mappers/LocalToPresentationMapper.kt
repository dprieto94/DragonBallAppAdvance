package com.dprieto.dragonballapp.data.mappers

import com.dprieto.dragonballapp.data.local.model.HeroRoomModel
import com.dprieto.dragonballapp.domain.HeroModel
import javax.inject.Inject

class LocalToPresentationMapper @Inject constructor(){

    fun map(heroLocalList: List<HeroRoomModel>): List<HeroModel>{
        return heroLocalList.map { map(it) }
    }

    fun map(hero: HeroRoomModel): HeroModel {
        return HeroModel(hero.id, hero.name, hero.photo, hero.favorite)
    }

}