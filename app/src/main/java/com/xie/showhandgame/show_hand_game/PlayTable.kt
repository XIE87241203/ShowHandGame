package com.xie.showhandgame.show_hand_game

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.xie.showhandgame.R
import com.xie.showhandgame.databinding.ViewPlayTableBinding

/**
 * @Author XJA87
 * @Date 2022/2/10 16:23
 * 牌桌
 */
class PlayTable : RelativeLayout {
    private lateinit var binding:ViewPlayTableBinding

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, detStyleAttr: Int) : super(
        context,
        attrs,
        detStyleAttr
    ) {
        initView()
    }

    private fun initView() {
        binding = ViewPlayTableBinding.inflate(LayoutInflater.from(context), this)
        setBackgroundResource(R.color.table_color)
    }

}