package com.xie.showhandgame.show_hand_game

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.xie.showhandgame.card.PockGroup
import com.xie.showhandgame.card.info.PokerCardInfo
import org.junit.Test
import org.junit.runner.RunWith

/**
 * @Author XJA87
 * @Date 2022/3/11 13:54
 */
@RunWith(AndroidJUnit4::class)
class CardComparatorTest {

    @Test
    fun compare() {
        val pockGroup = PockGroup(9)
        for(k in 0 until 50){
            pockGroup.initPocks()
            pockGroup.shuffle()
            val cards1 = ArrayList<PokerCardInfo>()
            for (i in 0 until 5) {
                val card = pockGroup.getOneCard() ?: return
                cards1.add(card)
            }
            val cards2 = ArrayList<PokerCardInfo>()
            for (i in 0 until 5) {
                val card = pockGroup.getOneCard() ?: return
                cards2.add(card)
            }
            val comparator = CardComparator()
            val value1 = comparator.getCardsValue(cards1)
            val value2 = comparator.getCardsValue(cards2)
            var result : Boolean
            try {
                result = comparator.compare(cards1, cards2)
            }catch (e:Exception){
                result = false
                e.printStackTrace()
            }
            var log1 = "card1->${comparator.sortCards(cards1)}; ${PockComp.getPockComp(value1).compName}; 分数 $value1"
            var log2 = "card2->${comparator.sortCards(cards2)}; ${PockComp.getPockComp(value2).compName}; 分数 $value2"
            if(result) log1 = "$log1 √" else log2 = "$log2 √"
            println(log1)
            println(log2)
            println("next")
        }

//        val cards1 = arrayListOf(
//            PokerCardInfo(9, CardSuit.SPADE),
//            PokerCardInfo(14, CardSuit.CLUB),
//            PokerCardInfo(11, CardSuit.CLUB),
//            PokerCardInfo(12, CardSuit.HEART),
//            PokerCardInfo(13, CardSuit.DIAMOND)
//        )
//
//        val cards2 = arrayListOf(
//            PokerCardInfo(10, CardSuit.DIAMOND),
//            PokerCardInfo(9, CardSuit.HEART),
//            PokerCardInfo(11, CardSuit.SPADE),
//            PokerCardInfo(12, CardSuit.DIAMOND),
//            PokerCardInfo(14, CardSuit.SPADE)
//        )
//
//        val comparator = CardComparator()
//        val value1 = comparator.getCardsValue(cards1)
//        val value2 = comparator.getCardsValue(cards2)
//        var result: Boolean
//        try {
//            result = comparator.compare(cards1, cards2)
//        } catch (e: Exception) {
//            result = false
//            e.printStackTrace()
//        }
//        var log1 =
//            "card1->${comparator.sortCards(cards1)}; ${PockComp.getPockComp(value1).compName}; 分数 $value1"
//        var log2 =
//            "card2->${comparator.sortCards(cards2)}; ${PockComp.getPockComp(value2).compName}; 分数 $value2"
//        if (result) log1 = "$log1 √" else log2 = "$log2 √"
//        println(log1)
//        println(log2)
//        println("next")
    }
}