package com.example.android_hangman_game.local_data_base

import androidx.room.Room
import com.example.android_hangman_game.AppRoot

object DatabaseBuilder {
    val roomDB by lazy {
        Room.databaseBuilder(
            AppRoot.instance.getContext(),
            TopPlayerDB::class.java,
            "Top.roomDB"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}