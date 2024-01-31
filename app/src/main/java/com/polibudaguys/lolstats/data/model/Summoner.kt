package com.polibudaguys.lolstats.data.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Summoner(
    @PrimaryKey val id: String?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "level") val level: Int?,
    @ColumnInfo(name = "tier") val tier: String?,
    @ColumnInfo(name = "rank") val rank: String?,
    @ColumnInfo(name = "points") val points: Int?,
    @ColumnInfo(name = "wins") val wins: Int?,
    @ColumnInfo(name = "losses") val losses: Int?,
    @ColumnInfo(name = "profile_icon_id") val profileIconId: Int?,
)