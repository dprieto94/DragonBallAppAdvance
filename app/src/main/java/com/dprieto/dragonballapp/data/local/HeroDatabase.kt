package com.dprieto.dragonballapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dprieto.dragonballapp.data.local.model.HeroRoomModel

@Database(entities = [HeroRoomModel::class], version = 1)
abstract class HeroDatabase: RoomDatabase() {
    abstract fun getDAO(): HeroDAO
}