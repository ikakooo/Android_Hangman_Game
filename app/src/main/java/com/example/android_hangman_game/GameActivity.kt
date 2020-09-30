package com.example.android_hangman_game

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_hangman_game.Extensions.printWithSpacesChars
import com.example.android_hangman_game.Extensions.printWordUnderscores
import com.example.android_hangman_game.data.GameData.lives
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        init()
    }


    @SuppressLint("SetTextI18n")
    private fun init(){
        val playerName = intent.getStringExtra("playerName")
        val incognitoWord =  intent.getStringExtra("incognitoWord")
        textViewID.text = """Hello $playerName, Let’s play Hangman!"""
        livesTextViewID.text = "Lives: $lives"
        showTextViewID.text = incognitoWord?.printWordUnderscores()?.let { printWithSpacesChars(it) }

        TryButtonID.setOnClickListener {
            EditTextID.text.toString()

        }

    }
}