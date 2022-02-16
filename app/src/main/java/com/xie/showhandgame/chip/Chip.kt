package com.xie.showhandgame.chip

import androidx.annotation.DrawableRes
import com.xie.showhandgame.R

/**
 * @Author XJA87
 * @Date 2022/2/10 16:28
 * 筹码
 */
enum class Chip(val value: Int, @DrawableRes val chipBackgroundId: Int, val size: Int) {
    oneHundred(100, R.drawable.bg_chip_blue, 40),
    twoHundred(200, R.drawable.bg_chip_green, 42),
    fiveHundred(500, R.drawable.bg_chip_purple, 44),
    oneThousand(1000, R.drawable.bg_chip_yellow, 46),
    fiveThousand(5000, R.drawable.bg_chip_red, 48),
    tenThousand(10000, R.drawable.bg_chip_black, 50);
}