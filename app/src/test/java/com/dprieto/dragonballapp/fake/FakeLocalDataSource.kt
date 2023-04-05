package com.dprieto.dragonballapp.fake

import com.dprieto.dragonballapp.data.local.LocalDataSource
import com.dprieto.dragonballapp.data.local.model.HeroRoomModel

class FakeLocalDataSource: LocalDataSource {
    override fun getHeros(): Result<List<HeroRoomModel>> {
        TODO("Not yet implemented")
    }

    override fun getHero(id: String): HeroRoomModel {
        TODO("Not yet implemented")
    }

    override fun insertHeros(localSuperherosList: List<HeroRoomModel>) {
        TODO("Not yet implemented")
    }

    override fun updateHero(hero: HeroRoomModel) {
        TODO("Not yet implemented")
    }

    override fun saveParam(id: String, value: String) {
        TODO("Not yet implemented")
    }

    override fun getParam(id: String): String {
        TODO("Not yet implemented")
    }
}