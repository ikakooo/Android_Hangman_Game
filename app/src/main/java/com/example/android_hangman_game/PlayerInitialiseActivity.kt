package com.example.android_hangman_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_hangman_game.Extensions.showTopPlayer
import com.example.android_hangman_game.local_data_base.DatabaseBuilder.roomDB
import kotlinx.android.synthetic.main.activity_initialise_player.*

class PlayerInitialiseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initialise_player)
        init()
        showTopPlayer()

        InitListTextViewID.showTopPlayer(10)
    }


    private fun init() {
        startGameButtonID.setOnClickListener {
            if (playerNameEditTextID.text.isNotEmpty() && incognitoWordEditTextID.text.isNotEmpty()) {
                startGameActivity(
                    playerNameEditTextID.text.toString(),
                    incognitoWordEditTextID.text.toString()
                )
            }
        }

    }


    private fun startGameActivity(playerName: String, incognitoWord: String) {
        val intent = Intent(this, GameActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.putExtra("playerName", playerName)
        intent.putExtra("incognitoWord", incognitoWord)
        startActivity(intent)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }

    private fun showTopPlayer(){
        val dB = roomDB.favoriteDaoConnection().getTopPlayers().toMutableList()
        (0 until dB.size).forEach {
          //  listTextViewID.text(RoomFavouriteMovieModel(dB[it].id?.toLong(),dB[it].movie_id.toString(),dB[it].path.toString(),dB[it].title.toString()))
        }
    }


}