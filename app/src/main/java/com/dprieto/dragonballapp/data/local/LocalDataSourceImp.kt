package com.dprieto.dragonballapp.data.local

import android.content.SharedPreferences
import com.dprieto.dragonballapp.data.local.model.HeroRoomModel
import javax.inject.Inject

class LocalDataSourceImp @Inject constructor(private val dao: HeroDAO, private val sharedPreferences: SharedPreferences): LocalDataSource {
    override fun getHeros(): Result<List<HeroRoomModel>> {
        return runCatching { dao.getAllHeros() }
    }

    override fun getHero(id: String): HeroRoomModel {
        return dao.getHero(id)
    }

    override fun insertHeros(localSuperherosList: List<HeroRoomModel>) {
        dao.insertAll(localSuperherosList)
    }

    override fun updateHero(hero: HeroRoomModel) {
        dao.updateHero(hero)
    }

    override fun saveParam(id: String, value: String) {
        sharedPreferences.edit().apply {
            putString(id, value)
        }.apply()
    }

    override fun getParam(id: String): String {
        return sharedPreferences.getString(id, "").toString()
    }
}