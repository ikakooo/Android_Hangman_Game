package com.example.android_hangman_game

import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import com.example.android_hangman_game.data.GameData.lives
import kotlinx.android.synthetic.main.dialog_layout.*

object Tools {
    @SuppressLint("SetTextI18n")
    fun winDialog(context: Context, case: Int) {
        val dialog = Dialog(context)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_layout)

        val params: ViewGroup.LayoutParams = dialog.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        params.height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog.window!!.attributes = params as WindowManager.LayoutParams
        dialog.dialogTitleTextViewID.text = "No Internet Connection"
        if (case == 0) dialog.imageViewWinOrLoseID.setImageResource(R.mipmap.gameover)
        dialog.show()

        dialog.dialogOkButton.setOnClickListener {
            lives = 8
            val intent = Intent(context, PlayerInitialiseActivity::class.java)
            val options = ActivityOptions.makeCustomAnimation(
                context, R.anim.diagonaltranslate,
                R.anim.top_out
            )
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent, options.toBundle())
            // overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}