package com.xie.showhandgame.show_hand_game

import android.util.Log
import com.xie.showhandgame.card.info.CardSuit
import com.xie.showhandgame.card.info.PokerCardInfo

/**
 * @Author XJA87
 * @Date 2022/2/10 14:57
 * 大于则返回1，小于返回-1，相等返回0
 */
class CardComparator {
    companion object {
        const val TAG = "CardComparator"

        //同花顺牌型分
        const val COMB_STRAIGHT_FLUSH = 1000000000

        //四条
        const val COMB_FOUR_OF_A_KIND = 100000000

        //三带一对
        const val COMB_FULL_HOUSE = 10000000

        //同花
        const val COMB_FLUSH = 1000000

        //顺子
        const val COMB_STRAIGHT = 100000

        //三条
        const val COMB_THREE_OF_A_KIND = 10000

        //对子
        const val COMB_PAIR = 1000

        //散牌（单牌最大分数为140）
        const val COMB_EMPTY = 0
    }

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

    fun compare(cards1: List<PokerCardInfo>, cards2: List<PokerCardInfo>): Boolean {
        //同花顺>四条>满堂红>同花>顺子>三条>二对>单对>散牌
        val cards1Value = getCardsValue(cards1)
        val cards2Value = getCardsValue(cards2)
        if (cards1Value > cards2Value) {
            return true
        } else if (cards1Value == cards2Value) {
            if (cards1Value == COMB_FLUSH) {
                //都是同花 先比数字再比花色，需要特殊处理
                for (i in cards1.indices) {
                    val sub = cards1[i].num - cards2[i].num
                    if (sub != 0) {
                        return sub > 0
                    }
                }
                //大小都一样，比花色
                return cards1[0].suit.code > cards2[0].suit.code
            } else {
                val errorMsg = "比较错误-->分数为$cards1Value"
                Log.e(TAG, errorMsg)
                throw Exception(errorMsg)
            }
        } else {
            return false
        }
    }

    fun getCardsValue(cards: List<PokerCardInfo>): Int {
        if (cards.isEmpty()) return 0
        //按数值和花色排序,小到大排
        cards.sortedBy {
            val cardComparator = CardComparator()
            cardComparator.getCartValue(it)
        }

        //组合分和牌面分
        var isFlush = true //同花

        var isStraight = true//顺子

        val sameNumList = ArrayList<Int>()
        val sameNumQtyList = ArrayList<Int>()
        //最大卡牌值
        val maxCardValue = getCartValue(cards[cards.size - 1])

        var sameNum = -1
        var sameNumQty = 0
        var firstCard: PokerCardInfo? = null
        for (index in cards.indices) {
            val cardValue = getCartValue(cards[index])

            if (index == 0) {
                firstCard = cards[index]
                sameNum = firstCard.num
            } else {
                firstCard?.let {
                    //第二张开始循环
                    //判断花色
                    isFlush = firstCard.suit.code == cards[index].suit.code

                    //判断顺子
                    if (isStraight) {
                        //顺子没有断开
                        isStraight = (cards[index].num - firstCard.num) == index
                    }

                    if (cards[index].num == sameNum) {
                        sameNumQty++
                    }

                    if (cards[index].num != sameNum || index == cards.size - 1) {
                        //和上一张不一样或者是最后一张时
                        if (sameNumQty > 0) {
                            //保存同数字的牌
                            sameNumList.add(sameNum)
                            //保存同数字的牌数
                            sameNumQtyList.add(sameNumQty + 1)
                        }
                        //刷新游标
                        sameNum = cards[index].num
                        sameNumQty = 0
                    }
                }
            }
        }

        var result = 0//牌的分数
        var maxSameNum = 0 //组合数较大的卡牌数字
        var maxSameNumQty = 0
        var minSameNumQty = 0
        if (sameNumQtyList.size > 1) {
            if (sameNumQtyList[0] > sameNumQtyList[1]) {
                //三条
                maxSameNum = sameNumList[0]
                maxSameNumQty = sameNumQtyList[0]
                minSameNumQty = sameNumQtyList[1]
            } else if (sameNumQtyList[0] < sameNumQtyList[1]) {
                //三条
                maxSameNum = sameNumList[1]
                maxSameNumQty = sameNumQtyList[1]
                minSameNumQty = sameNumQtyList[0]
            } else if (sameNumQtyList[0] == sameNumQtyList[1]) {
                //两对
                maxSameNum = sameNumList[1].coerceAtLeast(sameNumList[0])
                maxSameNumQty = sameNumQtyList[1]
                minSameNumQty = sameNumQtyList[0]
            }
        } else if (sameNumQtyList.size > 0) {
            maxSameNumQty = sameNumQtyList[0]
        }

        if (isFlush && isStraight) {
            //同花顺，先比大小再比花色
            // 分数=牌型分+大小分
            result += COMB_STRAIGHT_FLUSH
            result += maxCardValue
        } else if (maxSameNumQty == 4) {
            //四条
            result += COMB_FOUR_OF_A_KIND
            result += maxCardValue
        } else if (maxSameNumQty == 3 && minSameNumQty == 2) {
            //三带一对
            result += COMB_FULL_HOUSE
            //相同牌型下只比数字
            result += maxSameNum
        } else if (isFlush) {
            //同花
            result += COMB_FLUSH
            //相同牌型下先比数字再比花色，需要特殊处理
        } else if (isStraight) {
            //顺子
            result += COMB_STRAIGHT
            result += maxCardValue
        } else if (maxSameNumQty == 3) {
            //三条
            result += COMB_THREE_OF_A_KIND
            result += maxCardValue
        } else if (maxSameNumQty == 2 && minSameNumQty == 2) {
            //两对 比较对子里面最大的牌
            var maxPairValue = 0
            for (card in cards) {
                if (card.num == maxSameNum && getCartValue(card) > maxPairValue) {
                    maxPairValue = getCartValue(card)
                }
            }
            result += 2 * COMB_PAIR
            result += maxPairValue
        } else if (maxSameNumQty == 2) {
            //对子   比较对子里面最大的牌
            var maxPairValue = 0
            for (card in cards) {
                if (card.num == maxSameNum && getCartValue(card) > maxPairValue) {
                    maxPairValue = getCartValue(card)
                }
            }
            result += COMB_PAIR
            result += maxPairValue
        } else {
            //散牌
            result += COMB_EMPTY
            result += maxCardValue
        }
        return result
    }
}