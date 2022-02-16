package com.xie.showhandgame.chip

import androidx.annotation.DrawableRes
import com.xie.showhandgame.R

/**
 * @Author XJA87
 * @Date 2022/2/10 16:28
 * 筹码
 * @param size 直径单位dp
 */
enum class Chip(val value: Int, @DrawableRes val chipBackgroundId: Int, val size: Float) {
    oneHundred(100, R.drawable.bg_chip_blue, 40f),
    twoHundred(200, R.drawable.bg_chip_green, 42f),
    fiveHundred(500, R.drawable.bg_chip_purple, 44f),
    oneThousand(1000, R.drawable.bg_chip_yellow, 46f),
    fiveThousand(5000, R.drawable.bg_chip_red, 48f),
    tenThousand(10000, R.drawable.bg_chip_black, 50f);
}