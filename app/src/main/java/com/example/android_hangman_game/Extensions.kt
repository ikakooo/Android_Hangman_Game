package com.example.android_hangman_game

import com.example.android_hangman_game.data.GameData.triedChars

object Extensions {
    fun String.printWordUnderscores(): CharArray {
        val playerGuess = CharArray(toCharArray().size)
        for (i in playerGuess.indices) {
            playerGuess[i] = '_'
        }
        return playerGuess
    }

    fun printWithSpacesChars(array: CharArray): String {
        var word = ""
        for (i in array.indices) {
            word += array[i].toString() + " "
        }
        return word
    }

    fun isAlphabetOrNot(char: Char?): Boolean = char in 'a'..'z' || char in 'A'..'Z'

    fun isTriedCharacter(char: Char?): Boolean {
        triedChars.indices.forEach { if (triedChars[it].char == char) return true }
        return false
    }

}