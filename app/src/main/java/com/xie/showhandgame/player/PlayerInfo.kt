package com.xie.showhandgame.player

import com.xie.showhandgame.card.info.PokerCardInfo

/**
 * @Author XJA87
 * @Date 2022/2/10 15:57
 * 玩家信息
 * @param serialNumber 玩家序号
 */
class PlayerInfo(var score: Int, var name: String, var serialNumber: Int) {
    //手牌
    var cards = ArrayList<PokerCardInfo>()

    var isLose = false
}