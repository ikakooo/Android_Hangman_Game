package com.example.android_hangman_game.local_data_base

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_players")
data class RoomTopPlayerModel (
    @PrimaryKey(autoGenerate = true) val id : Long?=null,
    @ColumnInfo(name = "WinnerName") val WinnerName:String,
    @ColumnInfo(name = "WinnerLives") val WinnerLives:Int
)