package com.example.android_hangman_game

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.android_hangman_game.Extensions.isAlphabetOrNot
import com.example.android_hangman_game.Extensions.isTriedCharacter
import com.example.android_hangman_game.Extensions.printWithSpacesChars
import com.example.android_hangman_game.Extensions.printWordUnderscores
import com.example.android_hangman_game.Extensions.showTopPlayer
import com.example.android_hangman_game.Extensions.whatWillHappen
import com.example.android_hangman_game.data.CharsArray
import com.example.android_hangman_game.data.GameData.lives
import com.example.android_hangman_game.data.GameData.triedChars
import com.example.android_hangman_game.local_data_base.DatabaseBuilder.roomDB
import com.example.android_hangman_game.local_data_base.RoomTopPlayerModel
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {
    private var allUnderscoreIsNotOpened = true
    private var charIsNotHere = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        game()
        listTextViewID.showTopPlayer(7)
    }

    @SuppressLint("SetTextI18n")
    private fun game() {
        var playerName = intent.getStringExtra("playerName")
        var word = intent.getStringExtra("incognitoWord")
        if (playerName == null || word == null) {
            playerName = "jhb531454"
            word = "sdfsdfdsf"
        }


        textViewID.text = """Hello $playerName, Let’s play Hangman!"""
        livesTextViewID.text = "Lives: $lives"
        val incognitoWord = word.printWordUnderscores()


        inputCharAndCheck(word, incognitoWord,playerName)

        // savedPlayersScore.add(WinnersModel(playerName, lives))



        // insertRoomFavouriteMovieModel(RoomFavouriteMovieModel(movie_id = response.id, path = response.poster_path, title = response.original_title))


        if (lives <= 0) {
            Tools.winDialog(this@GameActivity, 0)
            //   println("Sorry, you lost… The word was: $Word")
            // println("Want to play again? (Y/N/H) ")
            whatWillHappen(this)
            addPlayerInDB(playerName)
        }
        if (!allUnderscoreIsNotOpened) {
            //Tools.winDialog(this@GameActivity, 1)
            // println("Congratulations! Want to play again? (Y, H or N:")
            whatWillHappen(this)
            addPlayerInDB(playerName)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun inputCharAndCheck(Word: String, incognitoWord: CharArray,playerName:String) {


        showTextViewID.text = printWithSpacesChars(incognitoWord)

        TryButtonID.setOnClickListener {


            val inputWord = EditTextID.text.toString()
            val inputtedCharInWord = if (inputWord.length == 1) inputWord[0] else '*'

            allUnderscoreIsNotOpened = false

            when {
                isTriedCharacter(inputtedCharInWord) && inputtedCharInWord != '*' -> {
                    allUnderscoreIsNotOpened = true
                    textViewID.text = "You already tried this character"
                    livesTextViewID.text = "Lives: $lives "
                    EditTextID.setText("")
                }
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //////////// ვამოწმებთ არის თუ არა ლათინური ალფაბეტური ////////////////////////////////////////////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                isAlphabetOrNot(inputtedCharInWord) && inputtedCharInWord != '*' -> {
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
                    if (!allUnderscoreIsNotOpened) {
                        Tools.winDialog(this@GameActivity, 1)
                        addPlayerInDB(playerName)
                        triedChars.clear()
                    }
                    when {
                        charIsNotHere -> {
                            textViewID.setTextColor(Color.RED)
                            textViewID.text = "There is no such character"
                            lives--
                            triedChars.add(CharsArray(inputtedCharInWord.toLowerCase()))
                            triedChars.add(CharsArray(inputtedCharInWord.toUpperCase()))
                        }
                        else -> textViewID.text = "Yes, it is there!!!"
                    }
                    charIsNotHere = true
                    livesTextViewID.text = "Lives: $lives "
                    //showTextViewID.text = incognitoWord.contentToString() ///ვბეჭდავთ ნაწილობრივ გამოცნობილ/დაფარულ სიტყვას/////
                    showTextViewID.text = printWithSpacesChars(incognitoWord)
                    EditTextID.setText("")
                }
                else -> {
                    ///////////////////////////// თუ არ არის ალფაბეტური, მაშინ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                    textViewID.setTextColor(Color.RED)
                    textViewID.text = "Please, enter a valid character"
                    allUnderscoreIsNotOpened = true
                    EditTextID.setText("")
                }
            }

            if (lives <= 0) {
                Tools.winDialog(this@GameActivity, 0)
                addPlayerInDB(playerName)
                triedChars.clear()
                //   println("Sorry, you lost… The word was: $Word")
                // println("Want to play again? (Y/N/H) ")
                whatWillHappen(this)
            }


        }


    }


    private fun addPlayerInDB(playerName:String){
        roomDB.favoriteDaoConnection().insertRoomTopPlayersModel(
            RoomTopPlayerModel(
                WinnerName = playerName,
                WinnerLives = lives
            )
        )
    }

}