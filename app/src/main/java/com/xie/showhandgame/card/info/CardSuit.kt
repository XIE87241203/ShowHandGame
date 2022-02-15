package com.xie.showhandgame.card.info

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import com.xie.showhandgame.R

/**
 * @Author XJA87
 * @Date 2022/2/10 14:18
 * 扑克牌花色
 */
enum class CardSuit(val code: Int, @ColorRes val colorId: Int, @DrawableRes val suitIconId: Int) {
    DIAMOND(0, R.color.card_red, R.drawable.ic_diamond),
    CLUB(1, R.color.card_black, R.drawable.ic_club),
    HEART(2, R.color.card_red, R.drawable.ic_heart),
    SPADE(3, R.color.card_black, R.drawable.ic_spade);
}