package com.xie.showhandgame.show_hand_game.info

/**
 * @Author XJA87
 * @Date 2022/2/10 16:03
 * 游戏的一些参数
 */
class GameConfig {
    companion object {
        const val MIN_POCK_NUM = 8

        //最大的牌是'A'
        const val MAX_POCK_NUM = 14
        //最大玩家数
        const val MAX_PLAYER_NUM = 5
        //最少玩家数
        const val MIN_PLAYER_NUM = 2

        //玩家初始分
        var playerInitScore = 5000

        //初始投注
        var initialBet = 200

        //最小加注
        var minBet = 100
    }
}