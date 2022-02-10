package com.xie.showhandgame.chip

import org.junit.Assert.*

import junit.framework.TestCase

import org.junit.Test

/**
 * @Author XJA87
 * @Date 2022/2/10 17:42
 */
class ChipHelperTest {

    @Test
    fun scoreToChips() {
//        assertEquals(4, 2 + 2)
        val chips =  ChipHelper.scoreToChips(17800)
        println(chips)
    }
}