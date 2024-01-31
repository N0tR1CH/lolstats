package com.polibudaguys.lolstats

import androidx.room.Database
import androidx.room.RoomDatabase
import com.polibudaguys.lolstats.data.daos.SummonerDao
import com.polibudaguys.lolstats.data.model.Summoner

@Database(entities = [Summoner::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun summonerDao(): SummonerDao
}