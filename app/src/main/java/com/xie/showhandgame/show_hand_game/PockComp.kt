package com.xie.showhandgame.show_hand_game

/**
 * @Author XJA87
 * @Date 2022/3/11 15:17
 */
enum class PockComp(val compName: String, val score: Int) {
    COMB_STRAIGHT_FLUSH("同花顺", 1000000000),
    COMB_FOUR_OF_A_KIND("四条", 100000000),
    COMB_FULL_HOUSE("满堂红", 10000000),
    COMB_FLUSH("同花", 1000000),
    COMB_STRAIGHT("顺子", 100000),
    COMB_THREE_OF_A_KIND("三条", 10000),
    COMB_TWO_PAIR("两对", 2000),
    COMB_PAIR("一对", 1000),
    COMB_EMPTY("无对", 0);

    companion object{
        fun getPockComp(value:Int):PockComp{
            return if(value / COMB_STRAIGHT_FLUSH.score >0){
                COMB_STRAIGHT_FLUSH
            }else if(value / COMB_FOUR_OF_A_KIND.score>0){
                COMB_FOUR_OF_A_KIND
            }else if(value / COMB_FULL_HOUSE.score>0){
                COMB_FULL_HOUSE
            }else if(value / COMB_FLUSH.score>0){
                COMB_FLUSH
            }else if(value / COMB_STRAIGHT.score>0){
                COMB_STRAIGHT
            }else if(value / COMB_THREE_OF_A_KIND.score>0){
                COMB_THREE_OF_A_KIND
            }else if(value / COMB_TWO_PAIR.score>0){
                COMB_TWO_PAIR
            }else if(value / COMB_PAIR.score>0){
                COMB_PAIR
            }else COMB_EMPTY
        }
    }
}