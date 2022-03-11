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
            if (cards1Value == PockComp.COMB_FLUSH.score) {
                //都是同花 先比数字再比花色，需要特殊处理
                val sortedCards1 = sortCards(cards1)
                val sortedCards2 = sortCards(cards2)
                for (i in sortedCards1.size-1 downTo 0) {
                    val sub = sortedCards1[i].num - sortedCards2[i].num
                    if (sub != 0) {
                        return sub > 0
                    }
                }
                //大小都一样，比花色
                return sortedCards1[0].suit.code > sortedCards2[0].suit.code
            } else {
                val errorMsg = "比较错误-->分数为$cards1Value"
                Log.e(TAG, errorMsg)
                throw Exception(errorMsg)
            }
        } else {
            return false
        }
    }

    /**
     * 从小到大排序卡牌
     */
    fun sortCards(cards: List<PokerCardInfo>): List<PokerCardInfo> {
        return cards.sortedBy {
            val cardComparator = CardComparator()
            cardComparator.getCartValue(it)
        }
    }

    fun getCardsValue(cards: List<PokerCardInfo>): Int {
        if (cards.isEmpty()) return 0
        //按数值和花色排序,小到大排
        val sortedCards = sortCards(cards)

        //组合分和牌面分
        var isFlush = true //同花

        var isStraight = true//顺子

        val sameNumList = ArrayList<Int>()
        val sameNumQtyList = ArrayList<Int>()
        //最大卡牌值
        val maxCardValue = getCartValue(sortedCards[sortedCards.size - 1])

        var sameNum = -1
        var sameNumQty = 0
        var firstCard: PokerCardInfo? = null
        for (index in sortedCards.indices) {

            if (index == 0) {
                firstCard = sortedCards[index]
                sameNum = firstCard.num
            } else {
                firstCard?.let {
                    //第二张开始循环
                    //判断花色
                    if(isFlush){
                        isFlush = firstCard.suit.code == sortedCards[index].suit.code
                    }

                    //判断顺子
                    if (isStraight) {
                        //顺子没有断开
                        isStraight = (sortedCards[index].num - firstCard.num) == index
                    }

                    if (sortedCards[index].num == sameNum) {
                        sameNumQty++
                    }

                    if (sortedCards[index].num != sameNum || index == sortedCards.size - 1) {
                        //和上一张不一样或者是最后一张时
                        if (sameNumQty > 0) {
                            //保存同数字的牌
                            sameNumList.add(sameNum)
                            //保存同数字的牌数
                            sameNumQtyList.add(sameNumQty + 1)
                        }
                        //刷新游标
                        sameNum = sortedCards[index].num
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
            maxSameNum = sameNumList[0]
            maxSameNumQty = sameNumQtyList[0]
        }

        if (isFlush && isStraight) {
            //同花顺，先比大小再比花色
            // 分数=牌型分+大小分
            result += PockComp.COMB_STRAIGHT_FLUSH.score
            result += maxCardValue
        } else if (maxSameNumQty == 4) {
            //四条
            result += PockComp.COMB_FOUR_OF_A_KIND.score
            result += maxCardValue
        } else if (maxSameNumQty == 3 && minSameNumQty == 2) {
            //三带一对
            result += PockComp.COMB_FULL_HOUSE.score
            //相同牌型下只比数字
            result += maxSameNum
        } else if (isFlush) {
            //同花
            result += PockComp.COMB_FLUSH.score
            //相同牌型下先比数字再比花色，需要特殊处理
        } else if (isStraight) {
            //顺子
            result += PockComp.COMB_STRAIGHT.score
            result += maxCardValue
        } else if (maxSameNumQty == 3) {
            //三条
            result += PockComp.COMB_THREE_OF_A_KIND.score
            result += maxCardValue
        } else if (maxSameNumQty == 2 && minSameNumQty == 2) {
            //两对 比较对子里面最大的牌
            var maxPairValue = 0
            for (card in cards) {
                if (card.num == maxSameNum && getCartValue(card) > maxPairValue) {
                    maxPairValue = getCartValue(card)
                }
            }
            result += PockComp.COMB_TWO_PAIR.score
            result += maxPairValue
        } else if (maxSameNumQty == 2) {
            //对子   比较对子里面最大的牌
            var maxPairValue = 0
            for (card in cards) {
                if (card.num == maxSameNum && getCartValue(card) > maxPairValue) {
                    maxPairValue = getCartValue(card)
                }
            }
            result += PockComp.COMB_PAIR.score
            result += maxPairValue
        } else {
            //散牌
            result += PockComp.COMB_EMPTY.score
            result += maxCardValue
        }
        return result
    }
}