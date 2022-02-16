package com.xie.showhandgame

import android.content.Context
import android.util.TypedValue

/**
 * @Author XJA87
 * @Date 2022/2/15 16:33
 */
class Tools {

    companion object{
        /**
         * dp转px
         *
         * @param context
         * @param n
         * @return
         */
        fun dp2px(context: Context, n: Float): Int {
            val metrics = context.resources.displayMetrics
            val result = (n * metrics.density)
            return result.toInt()
        }

        /**
         * 将dp转换成px
         *
         * @param context
         * @param valueInDp
         * @return
         */
        fun dpToPx(context: Context, valueInDp: Float): Float {
            val metrics = context.resources.displayMetrics
            return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, valueInDp, metrics)
        }

        fun getRandom(max: Int, min: Int): Int {
            return (Math.random() * (max - min + 1) + min).toInt()
        }
    }
}