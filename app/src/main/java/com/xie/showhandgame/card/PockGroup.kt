package com.xie.showhandgame.card

import com.xie.showhandgame.card.info.CardSuit
import com.xie.showhandgame.card.info.PokerCardInfo

/**
 * @Author XJA87
 * @Date 2022/2/10 15:26
 * 牌组
 * @param minPockNum 牌组内最小的牌
 * @param maxPockNum 牌组内最大的牌
 */
class PockGroup(
    val minPockNum: Int = PokerCardInfo.MIN_NUM,
    val maxPockNum: Int = PokerCardInfo.MAX_NUM
) {
    private val pocks = ArrayList<PokerCardInfo>()

    /**
     * 初始化牌组
     */
    fun initPocks() {
        pocks.clear()
        //遍历牌的num
        for (num in minPockNum..maxPockNum) {
            //遍历牌的花色
            for (suit in CardSuit.values()) {
                //把牌加入牌组
                pocks.add(PokerCardInfo(num, suit))
            }
        }
    }

    /**
     * 洗牌
     */
    fun shuffle() {
        pocks.shuffle()
    }

    fun isEmpty() = pocks.isEmpty()

    fun size() = pocks.size

    /**
     * 发一张牌
     */
    fun getOneCard(): PokerCardInfo? {
        return if (pocks.isNotEmpty()) {
            pocks.removeAt(0)
        } else {
            null
        }
    }
}