package com.xie.showhandgame.chip

import android.util.SparseArray

/**
 * @Author XJA87
 * @Date 2022/2/10 16:50
 * 筹码助手
 */
object ChipHelper {
    val chipMap: Map<Int, Chip>

    init {
        chipMap = HashMap()
        for (chip in Chip.values()) {
            chipMap.put(chip.value, chip)
        }
    }

    /**
     * 筹码换成分数
     */
    fun chipsToScore(chips:MutableList<Chip>):Int{
        var result = 0
        for (chip in chips){
            result += chip.value
        }
        return result
    }

    /**
     * 分数换成筹码
     */
    fun scoreToChips(score: Int): MutableList<Chip> {
        var tempScore = score
        val result = ArrayList<Chip>()
        val chipValues = ArrayList<Int>()
        chipValues.addAll(chipMap.keys)
        //小到大排序
        chipValues.sort()
        //倒序,大到小排序
        chipValues.reverse()
        //分数大于最小分数，进入循环
        while (tempScore >= chipValues[chipValues.size - 1]) {
            valueFor@ for (chipValue in chipValues) {
                if (tempScore >= chipValue) {
                    val tempChip = chipMap[chipValue]
                    if (tempChip != null) {
                        result.add(tempChip)
                    }
                    tempScore -= chipValue
                    break@valueFor
                }
            }
        }
        return result
    }
}