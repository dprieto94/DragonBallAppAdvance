package com.dprieto.dragonballapp.fake

import com.dprieto.dragonballapp.data.local.LocalDataSource
import com.dprieto.dragonballapp.data.local.model.HeroRoomModel
import com.dprieto.dragonballapp.utils.generateHerosLocal

class FakeLocalDataSource(private val expectedStatus: String): LocalDataSource {


    override fun getHeros(): Result<List<HeroRoomModel>> {
        return when(expectedStatus){
            "SUCCESS" -> Result.success(generateHerosLocal())
            "ERROR" -> Result.failure(Exception("Error"))
            "EMPTY" -> Result.success(emptyList())
            else -> {Result.failure(Exception())}
        }
    }

    override fun getHero(id: String): HeroRoomModel {
        return generateHerosLocal().first()
    }

    override fun insertHeros(localSuperherosList: List<HeroRoomModel>) {

    }

    override fun updateHero(hero: HeroRoomModel) {

    }

    override fun saveParam(id: String, value: String) {
        TODO("Not yet implemented")
    }

    override fun getParam(id: String): String {
        TODO("Not yet implemented")
    }
}