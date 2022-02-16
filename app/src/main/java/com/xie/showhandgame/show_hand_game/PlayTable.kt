package com.xie.showhandgame.show_hand_game

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.TranslateAnimation
import android.widget.RelativeLayout
import com.xie.showhandgame.R
import com.xie.showhandgame.Tools
import com.xie.showhandgame.chip.Chip
import com.xie.showhandgame.chip.ChipHelper
import com.xie.showhandgame.chip.ChipView
import com.xie.showhandgame.databinding.ViewPlayTableBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * @Author XJA87
 * @Date 2022/2/10 16:23
 * 牌桌
 */
class PlayTable : RelativeLayout {
    private lateinit var binding: ViewPlayTableBinding

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, detStyleAttr: Int) : super(
        context,
        attrs,
        detStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        binding = ViewPlayTableBinding.inflate(LayoutInflater.from(context), this)
        setBackgroundResource(R.color.table_color)
    }


    suspend fun anteChip(playerPosition: PlayerPosition, chipsValue: Int) {
        val maxChipSize = Tools.dp2px(context, Chip.tenThousand.size)
        val endYStart = binding.vChipGround.top
        val endYEnd = binding.vChipGround.bottom - maxChipSize
        val endXStart = binding.vChipGround.left
        val endXEnd = binding.vChipGround.right - maxChipSize
        val chipR = maxChipSize / 2

        val startLocation = when (playerPosition) {
            PlayerPosition.TOP -> {
                //正上方
                Location(((right - left) / 2) - chipR, top - maxChipSize)
            }
            PlayerPosition.LEFT -> {
                //正左
                Location(left - maxChipSize, ((bottom - top) / 2) - chipR)
            }
            PlayerPosition.RIGHT -> {
                Location(right + maxChipSize, ((bottom - top) / 2) - chipR)
            }
            else -> {
                Location(((right - left) / 2) - chipR, bottom + maxChipSize)
            }
        }
        val chips = ChipHelper.scoreToChips(chipsValue)
        withContext(Dispatchers.Main) {
            //添加筹码和播放动画
            binding.tvGameHint.text = String.format(
                "%s %s",
                context.getString(R.string.ante_text),
                chipsValue
            )
            for (chip in chips) {
                val endLocation = Location(
                    Tools.getRandom(endXStart, endXEnd),
                    Tools.getRandom(endYStart, endYEnd)
                )
                val chipView = ChipView(context)
                chipView.setChipInfo(chip)
                binding.rlChipTable.addView(chipView, 0)

                val translateAnimation = TranslateAnimation(
                    startLocation.x.toFloat(),
                    endLocation.x.toFloat(),
                    startLocation.y.toFloat(),
                    endLocation.y.toFloat()
                )
                translateAnimation.duration = 1000
                translateAnimation.fillAfter = true
                chipView.startAnimation(translateAnimation)
            }
        }
        //等待动画播完
        delay(1000)
    }

    fun clearChip() {
        for (i in 0 until binding.rlChipTable.childCount) {
            val view = getChildAt(i)
            if (view is ChipView) {
                view.clearAnimation()
            }
        }
        binding.rlChipTable.removeAllViews()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        clearChip()
    }

    private data class Location(val x: Int, val y: Int)

    enum class PlayerPosition {
        LEFT, TOP, RIGHT, BOTTOM
    }
}