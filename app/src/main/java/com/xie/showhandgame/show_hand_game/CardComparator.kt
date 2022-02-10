package com.xie.showhandgame.show_hand_game

import com.xie.showhandgame.card.info.CardSuit
import com.xie.showhandgame.card.info.PokerCardInfo

/**
 * @Author XJA87
 * @Date 2022/2/10 14:57
 * 大于则返回1，小于返回-1，相等返回0
 */
class CardComparator {

    /**
     * 获取牌的分值
     * 数字分为十位，花色分为个位
     */
    fun getCartValue(card: PokerCardInfo): Int {
        return card.num * 10 + getCardSuitValue(card.suit)
    }

    /**
     * 获取牌的花色分
     * 花色分为个位
     */
    fun getCardSuitValue(cardSuit: CardSuit): Int {
        return cardSuit.code
    }

    /**
     * 比较牌的大小
     */
    fun compare(o1: PokerCardInfo, o2: PokerCardInfo): Int {
        return when {
            getCartValue(o1) - getCartValue(o2) > 0 -> {
                1
            }
            getCartValue(o1) - getCartValue(o2) == 0 -> {
                0
            }
            else -> {
                -1
            }
        }
    }

    /**
     * 比较牌的花色大小
     */
    fun compare(o1: CardSuit, o2: CardSuit): Int {
        return when {
            getCardSuitValue(o1) - getCardSuitValue(o2) > 0 -> {
                1
            }
            getCardSuitValue(o1) - getCardSuitValue(o2) == 0 -> {
                0
            }
            else -> {
                -1
            }
        }
    }
}