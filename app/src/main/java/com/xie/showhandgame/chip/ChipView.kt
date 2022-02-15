package com.xie.showhandgame.chip

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.RelativeLayout
import com.xie.showhandgame.databinding.ViewChipBinding

/**
 * @Author XJA87
 * @Date 2022/2/15 16:12
 */
class ChipView : RelativeLayout {
    private lateinit var binding: ViewChipBinding

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
        binding = ViewChipBinding.inflate(LayoutInflater.from(context), this)
    }

    fun setChipInfo(info:Chip){
        binding.tvValue.text = info.value.toString()
        binding.tvValue.setBackgroundResource(info.suitIconId)
    }
}