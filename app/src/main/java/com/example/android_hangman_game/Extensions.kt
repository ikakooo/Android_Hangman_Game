package com.example.android_hangman_game

import com.example.android_hangman_game.data.GameData.lives
import com.example.android_hangman_game.data.GameData.savedPlayersScore
import com.example.android_hangman_game.data.GameData.triedChars
import com.example.android_hangman_game.data.GameData.weArePlaying

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

    fun whatWillHappen() {

        val inputWord = readLine()
        val answer = if (inputWord?.length == 1) inputWord[0] else '*'
        when {
            answer.toLowerCase() == 'y' -> {
                weArePlaying = true
                lives = 8
                triedChars.clear()
            }
            answer.toLowerCase() == 'h' -> {
                savedPlayersScore.sortBy { it.WinnerLives }
                val reversed = savedPlayersScore.asReversed()
                reversed.indices.forEach {
                    if (it < 5) println("${it + 1})  ${reversed[it].WinnerName} - ${reversed[it].WinnerLives} Lives")
                }


                println("Want to play again? (Y/N/H): ")
                repeat(1) { whatWillHappen() }
            }
            answer.toLowerCase() == 'n' -> {
                weArePlaying = false
                println("Thanks for playing!")

            }
        }
    }

}