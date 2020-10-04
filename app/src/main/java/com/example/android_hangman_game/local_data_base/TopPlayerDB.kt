package com.example.android_hangman_game.local_data_base

import androidx.room.*

@Database(entities = [RoomTopPlayerModel::class], version = 1)
abstract class TopPlayerDB: RoomDatabase(){
    abstract fun favoriteDaoConnection() : TopPlayerDao
}