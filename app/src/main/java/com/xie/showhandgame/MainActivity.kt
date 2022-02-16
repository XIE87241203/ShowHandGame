package com.xie.showhandgame

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xie.showhandgame.databinding.ActivityMainBinding
import com.xie.showhandgame.show_hand_game.GameActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartGame.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }
    }
}