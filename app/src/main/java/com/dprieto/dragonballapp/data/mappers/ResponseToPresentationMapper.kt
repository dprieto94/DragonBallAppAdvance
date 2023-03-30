package com.dprieto.dragonballapp.data.mappers

import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import com.dprieto.dragonballapp.domain.HeroModel
import com.dprieto.dragonballapp.domain.HeroModelDetail
import javax.inject.Inject

class ResponseToPresentationMapper @Inject constructor(){

    fun map(heroList: List<HeroResponse>): List<HeroModel>{
        return heroList.map { map(it) }
    }

    fun map(hero: HeroResponse): HeroModel{
        return HeroModel(hero.id, hero.name, hero.photo, hero.favorite)
    }

}