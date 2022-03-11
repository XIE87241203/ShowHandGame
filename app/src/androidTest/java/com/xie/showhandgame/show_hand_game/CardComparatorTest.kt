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
        val pockGroup = PockGroup()
        pockGroup.initPocks()
        for(j in 0 until 20){
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
            println("card1->$cards1")
            println("card2->$cards2")
            println("result->${comparator.compare(cards1, cards2)}")
        }
    }
}