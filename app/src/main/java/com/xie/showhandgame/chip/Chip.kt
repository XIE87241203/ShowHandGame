package com.xie.showhandgame.chip

import androidx.annotation.DrawableRes
import com.xie.showhandgame.R

/**
 * @Author XJA87
 * @Date 2022/2/10 16:28
 * 筹码
 */
enum class Chip(val value: Int, @DrawableRes val suitIconId: Int) {
    oneHundred(100, R.drawable.bg_chip_blue),
    twoHundred(200, R.drawable.bg_chip_green),
    fiveHundred(500, R.drawable.bg_chip_purple),
    oneThousand(1000, R.drawable.bg_chip_yellow),
    fiveThousand(5000, R.drawable.bg_chip_red),
    tenThousand(10000, R.drawable.bg_chip_black);
}