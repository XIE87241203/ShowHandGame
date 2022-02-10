package com.xie.showhandgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.xie.showhandgame.card.PockGroup
import com.xie.showhandgame.show_hand_game.info.GameConfig

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pockGroup = PockGroup(GameConfig.MIN_POCK_NUM, GameConfig.MAX_POCK_NUM)
        pockGroup.initPocks()
        pockGroup.shuffle()
        Log.i("testMsg", "onCreate: " + pockGroup.size())
    }
}