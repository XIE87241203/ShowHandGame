package com.xie.showhandgame.card.info

/**
 * @Author XJA87
 * @Date 2022/2/10 14:15
 */
class PokerCardInfo{
    val suit: CardSuit
    val num: Int
    val displayChar: String

    constructor(num: Int, suit: CardSuit) {
        this.suit = suit
        this.num = num
        displayChar = getNumDisplayString(num)
    }


    companion object {
        const val MAX_NUM = 14
        const val MIN_NUM = 2

        /**
         * 获取牌展示的字符
         */
        fun getNumDisplayString(num: Int): String {
            return when (num) {
                in MIN_NUM..10 -> {
                    num.toString()
                }
                11 -> "J"
                12 -> "Q"
                13 -> "K"
                else -> "A"
            }
        }
    }

}