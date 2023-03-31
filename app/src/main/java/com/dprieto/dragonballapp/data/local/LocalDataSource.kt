package com.dprieto.dragonballapp.data.local

import com.dprieto.dragonballapp.data.local.model.HeroRoomModel

interface LocalDataSource {

    fun getHeros(): Result<List<HeroRoomModel>>

    fun insertHeros(localSuperherosList: List<HeroRoomModel>)

    fun updateHero(hero: HeroRoomModel)
}