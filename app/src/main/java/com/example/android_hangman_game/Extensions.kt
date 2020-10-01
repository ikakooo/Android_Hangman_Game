package com.example.android_hangman_game

import android.annotation.SuppressLint
import android.app.Activity
import com.example.android_hangman_game.data.GameData.lives
import com.example.android_hangman_game.data.GameData.savedPlayersScore
import com.example.android_hangman_game.data.GameData.triedChars
import com.example.android_hangman_game.data.GameData.weArePlaying
import kotlinx.android.synthetic.main.activity_game.*
import java.security.AccessControlContext

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

    @SuppressLint("SetTextI18n")
    fun whatWillHappen(context: Activity) {

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
                    if (it < 5) context.listTextViewID.text = "${it + 1})  ${reversed[it].WinnerName} - ${reversed[it].WinnerLives} Lives"
                }

                context.textViewID.text = "Want to play again? (Y/N/H): "

                repeat(1) { whatWillHappen(context) }
            }
            answer.toLowerCase() == 'n' -> {
                weArePlaying = false
                context.textViewID.text = "Thanks for playing!"

            }
        }
    }

}