package com.xie.showhandgame.show_hand_game

import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.xie.showhandgame.R

/**
 * @Author XJA87
 * @Date 2022/2/16 10:38
 */
class GameActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.BLACK
    }
}