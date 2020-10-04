package com.example.android_hangman_game.local_data_base

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TopPlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRoomTopPlayersModel(TopPlayers: RoomTopPlayerModel)

    @Query("select * from top_players ORDER BY WinnerLives ASC,WinnerLives DESC; ")
    fun getTopPlayers(): List<RoomTopPlayerModel>

    @Query("delete from top_players where WinnerName = :id")
    fun deleteTopPlayers(id: String)

    @Query("Select * from top_players where WinnerName =  :id")
    fun isTopPlayers(id: String): RoomTopPlayerModel
}


