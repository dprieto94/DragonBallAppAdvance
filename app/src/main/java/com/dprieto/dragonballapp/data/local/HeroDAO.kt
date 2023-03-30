package com.dprieto.dragonballapp.data.local

import androidx.room.*
import com.dprieto.dragonballapp.data.local.model.HeroRoomModel

@Dao
interface HeroDAO {

    @Query("SELECT * FROM heros")
    fun getAllHeros(): List<HeroRoomModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(superHero: List<HeroRoomModel>)

    @Update
    fun updateHero(superHero: HeroRoomModel)
}