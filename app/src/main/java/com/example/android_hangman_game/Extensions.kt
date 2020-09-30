package com.example.android_hangman_game

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

}