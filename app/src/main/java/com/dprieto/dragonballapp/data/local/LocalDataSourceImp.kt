package com.dprieto.dragonballapp.data.local

import com.dprieto.dragonballapp.data.local.model.HeroRoomModel
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val dao: HeroDAO): LocalDataSource {
    override fun getHeros(): Result<List<HeroRoomModel>> {
        return runCatching { dao.getAllHeros() }
    }

    override fun insertHeros(localSuperherosList: List<HeroRoomModel>) {
        dao.insertAll(localSuperherosList)
    }

    override fun updateHero(hero: HeroRoomModel) {
        dao.updateHero(hero)
    }
}