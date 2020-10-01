package com.example.android_hangman_game.data

object GameData {
    var weArePlaying = true
    var lives = 8
    var triedChars = mutableListOf<CharsArray>()
    var savedPlayersScore = mutableListOf<WinnersModel>()
}