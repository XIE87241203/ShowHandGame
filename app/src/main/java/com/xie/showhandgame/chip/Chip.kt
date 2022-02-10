package com.xie.showhandgame.chip

import android.util.SparseArray
import androidx.annotation.IdRes

/**
 * @Author XJA87
 * @Date 2022/2/10 16:28
 * 筹码
 */
enum class Chip(val value: Int, @IdRes val suitIconId: Int) {
    oneHundred(100, 0),
    twoHundred(200, 0),
    fiveHundred(500, 0),
    oneThousand(1000, 0),
    fiveThousand(5000, 0),
    tenThousand(10000, 0);

    // TODO: 补齐筹码图片
}