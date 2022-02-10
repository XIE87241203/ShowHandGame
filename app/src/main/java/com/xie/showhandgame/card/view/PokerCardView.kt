package com.xie.showhandgame.card.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.xie.showhandgame.databinding.ViewPlayingCardBinding

/**
 * @Author XJA87
 * @Date 2022/2/10 12:13
 * 扑克牌
 */
class PokerCardView : ConstraintLayout {
    private lateinit var binding:ViewPlayingCardBinding

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
        binding = ViewPlayingCardBinding.inflate(LayoutInflater.from(context), this)

    }
}