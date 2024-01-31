package com.polibudaguys.lolstats.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.polibudaguys.lolstats.data.model.Summoner

@Dao
interface SummonerDao {
    @Query("SELECT * FROM summoner")
    fun getAll(): List<Summoner>

    @Insert
    fun insert(vararg summoner: Summoner)

    @Delete
    fun delete(summoner: Summoner)

    // Check for summoner existence by id
    @Query("SELECT EXISTS(SELECT * FROM summoner WHERE id = :id)")
    fun exists(id: String): Boolean
}