package com.xie.showhandgame.card.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.xie.showhandgame.card.info.PokerCardInfo
import com.xie.showhandgame.databinding.ViewPokerCardBinding

/**
 * @Author XJA87
 * @Date 2022/2/10 12:13
 * 扑克牌
 */
class PokerCardView : ConstraintLayout {
    private lateinit var binding: ViewPokerCardBinding
    private lateinit var cardInfo: PokerCardInfo

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
        binding = ViewPokerCardBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setInfo(info: PokerCardInfo) {
        cardInfo = info
        binding.tvNum.setTextColor(ContextCompat.getColor(context,info.suit.colorId))
        binding.tvNum.text = info.displayChar
        binding.ivIcon.setImageResource(info.suit.suitIconId)
    }
}