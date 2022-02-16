package com.xie.showhandgame.show_hand_game

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.xie.showhandgame.databinding.ActivityGameBinding
import kotlinx.coroutines.launch

/**
 * @Author XJA87
 * @Date 2022/2/16 10:38
 */
class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.BLACK

        binding.btnTestAnimLeft.setOnClickListener {
            binding.playTable.clearChip()
            lifecycleScope.launch {
                binding.playTable.anteChip(
                    PlayTable.PlayerPosition.LEFT,
                    17800
                )
                Log.i("testMsg", "onCreate: 丢筹码完成")
            }
        }
        binding.btnTestAnimTop.setOnClickListener {
            binding.playTable.clearChip()
            lifecycleScope.launch {
                binding.playTable.anteChip(PlayTable.PlayerPosition.TOP, 7800)
            }
        }
        binding.btnTestAnimRight.setOnClickListener {
            binding.playTable.clearChip()
            lifecycleScope.launch {
                binding.playTable.anteChip(
                    PlayTable.PlayerPosition.RIGHT,
                    800
                )
            }

        }
        binding.btnTestAnimBottom.setOnClickListener {
            binding.playTable.clearChip()
            lifecycleScope.launch {
                binding.playTable.anteChip(
                    PlayTable.PlayerPosition.BOTTOM,
                    100
                )
            }
        }
    }
}