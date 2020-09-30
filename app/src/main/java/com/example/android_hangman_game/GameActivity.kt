package com.example.android_hangman_game

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_hangman_game.Extensions.isAlphabetOrNot
import com.example.android_hangman_game.Extensions.isTriedCharacter
import com.example.android_hangman_game.Extensions.printWithSpacesChars
import com.example.android_hangman_game.Extensions.printWordUnderscores
import com.example.android_hangman_game.data.CharsArray
import com.example.android_hangman_game.data.GameData.lives
import com.example.android_hangman_game.data.GameData.triedChars
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    var allUnderscoreIsNotOpened = true
    var charIsNotHere = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        init()
    }


    @SuppressLint("SetTextI18n")
    private fun init(){
        var playerName = intent.getStringExtra("playerName")
        var Word =  intent.getStringExtra("incognitoWord")
        if (playerName == null||Word==null) {
            playerName = "jhb531454"
            Word = "sdfsdfdsf"
        }
        textViewID.text = """Hello $playerName, Let’s play Hangman!"""
        livesTextViewID.text = "Lives: $lives"
        val incognitoWord = printWithSpacesChars(Word.printWordUnderscores()).toCharArray()
        showTextViewID.text =incognitoWord.toString()

        TryButtonID.setOnClickListener {
            val inputWord =  EditTextID.text.toString()
            val inputtedCharInWord = inputWord[0]

            allUnderscoreIsNotOpened = false

            when {
                isTriedCharacter(inputtedCharInWord) -> {
                    allUnderscoreIsNotOpened = true
                    textViewID.text =  "You already tried this character"
                    livesTextViewID.text = "Lives: $lives "
                }
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //////////// ვამოწმებთ არის თუ არა ლათინური ალფაბეტური ////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                isAlphabetOrNot(inputtedCharInWord) -> {
                    /////////////// შევდივრთ ციკლში სადაც ხდება შეყვანილი ჩარაქთერის შემმოწმება/შედარება/ვალიდაცია/დამატება/დამახსოვრება   //////////////////
                    (Word.indices).forEach {
                        if (inputtedCharInWord.toLowerCase() == Word[it].toLowerCase()) {
                            ///////// აქ ხდება თითოეული ჩარაქთერის გახსნა/ამოცნობა/შედარება  ////////////
                            incognitoWord[it] = Word[it]
                            triedChars.add(CharsArray(inputtedCharInWord.toLowerCase()))
                            triedChars.add(CharsArray(inputtedCharInWord.toUpperCase()))//// ვამატებთ დაბალი და მაღალი რეგისტრის ჩარაქთერებს

                            charIsNotHere = false
                        }

                        if (incognitoWord[it] == '_') {
                            //////// თუ ყველა ქვედატირე გახსნილი არ არის, მაშინ ვიმეორებთ ციკლს //////////////////////
                            allUnderscoreIsNotOpened = true
                        }

                    }
                    when {
                        charIsNotHere -> {
                            textViewID.text ="There is no such character"
                            lives--
                            triedChars.add(CharsArray(inputtedCharInWord.toLowerCase()))
                            triedChars.add(CharsArray(inputtedCharInWord.toUpperCase()))
                        }
                        else ->textViewID.text ="Yes, it is there!!!"
                    }
                    charIsNotHere = true
                    livesTextViewID.text = "Lives: $lives "
                    showTextViewID.text = incognitoWord.toString() ///ვბეჭდავთ ნაწილობრივ გამოცნობილ/დაფარულ სიტყვას/////


                }
                else -> {
                    ///////////////////////////// თუ არ არის ალფაბეტური, მაშინ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    textViewID.setTextColor(Color.RED)
                    textViewID.text = "Please, enter a valid character"
                    allUnderscoreIsNotOpened = true
                }
            }



        }

    }
}