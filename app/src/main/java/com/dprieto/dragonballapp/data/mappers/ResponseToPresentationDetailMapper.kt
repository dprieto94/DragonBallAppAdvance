package com.dprieto.dragonballapp.data.mappers

import com.dprieto.dragonballapp.data.remote.response.HeroResponse
import com.dprieto.dragonballapp.domain.HeroModel
import com.dprieto.dragonballapp.domain.HeroModelDetail
import javax.inject.Inject

class ResponseToPresentationDetailMapper @Inject constructor(){


    fun map(hero: HeroResponse): HeroModelDetail {
        return HeroModelDetail(hero.id, hero.name, hero.photo, hero.description, hero.favorite)
    }

}