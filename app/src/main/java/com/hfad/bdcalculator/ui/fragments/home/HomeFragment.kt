package com.hfad.bdcalculator.ui.fragments.home

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.hfad.bdcalculator.R
import com.hfad.bdcalculator.core.ui.base.BaseFragment
import com.hfad.bdcalculator.data.local.room.History
import com.hfad.bdcalculator.databinding.FragmentHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), View.OnClickListener, OnHistory {

    override val viewModel: HomeViewModel by viewModel()

    override fun setupLiveData() {}

    override fun setupUI() {
        binding.zero.setAnimation()
        binding.one.setAnimation()
        binding.two.setAnimation()
        binding.three.setAnimation()
        binding.four.setAnimation()
        binding.five.setAnimation()
        binding.six.setAnimation()
        binding.seven.setAnimation()
        binding.eight.setAnimation()
        binding.nine.setAnimation()
        binding.parentheses.setAnimation()
        binding.percentage.setAnimation()
        binding.div.setAnimation()
        binding.multiply.setAnimation()
        binding.minus.setAnimation()
        binding.addition.setAnimation()
        binding.equal.setAnimation()
        binding.comma.setAnimation()
        binding.c.setAnimation()
        binding.plusMinus.setAnimation()

        binding.backSpace.setOnClickListener(this)
        binding.imageClock.setOnClickListener(this)
        binding.clearHistory.setOnClickListener(this)
        binding.imageRuler.setOnClickListener(this)

        observers()
    }

    private fun observers() {
        viewModel.typedLiveData.observe(viewLifecycleOwner) {
            binding.textMain.setText(it)
            binding.textMain.setSelection(binding.textMain.text.length)
            changeFontSize()
        }

        viewModel.eventEqualClicked.observe(viewLifecycleOwner) {
            if (it) paintGreen() else paintBlack()
        }

        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            binding.textResult.text = it.toString()
            binding.textMain.textSize = 35f
        }

        viewModel.fromRoom.observe(viewLifecycleOwner) {
            binding.recyclerView.apply {
                this.adapter = HistoryAdapter(it, this@HomeFragment)
                if (it.isNotEmpty()) this.smoothScrollToPosition(it.size - 1)
            }
        }
    }

    private fun TextView.setAnimation() {
        this.setOnClickListener {

            var startSize = 26f
            val endSize = 15f

            val colorFrom = resources.getColor(R.color.blackish)
            var colorTo = resources.getColor(R.color.whitish)

            if (it.id == binding.addition.id || it.id == binding.minus.id || it.id == binding.div.id) startSize =
                40f
            else if (it.id == binding.multiply.id) startSize = 30f
            else if (it.id == binding.parentheses.id) startSize = 22f
            else if (it.id == binding.equal.id) {
                startSize = 37f;colorTo = resources.getColor(R.color.green)
            }

            val animationDuration: Long = 300
            val animator = ValueAnimator.ofFloat(endSize, startSize)
            animator.duration = animationDuration

            animator.addUpdateListener { valueAnimator ->
                val animatedValue = valueAnimator.animatedValue as Float
                this.textSize = animatedValue
            }

            animator.start()

            val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
            colorAnimation.duration = animationDuration

            colorAnimation.addUpdateListener { animator -> this.setBackgroundColor(animator.animatedValue as Int) }
            colorAnimation.start()

            onClick(this)
        }
    }

    private fun changeFontSize() {
        val lineEndIndex: Int = binding.textMain.text.toString().indexOf("\n")
        val firstLineLength: Int = if (lineEndIndex == -1) {
            binding.textMain.text.toString().length
        } else {
            lineEndIndex;
        }
        if (firstLineLength > 14) binding.textMain.textSize = 20f
    }

    private fun paintGreen() {
        binding.textMain.setTextColor(resources.getColor(R.color.green))
    }

    private fun paintBlack() {
        binding.textMain.setTextColor(resources.getColor(R.color.black))
    }

    private fun adjustVisibility() {
        binding.historyContainer.isVisible = !binding.historyContainer.isVisible
        binding.historyLine.isVisible = !binding.historyLine.isVisible
    }

    override fun onHistoryClick(history: History) {
        viewModel.setHistory(history)
    }


    override fun onClick(v: View?) {
        if (v?.id == binding.imageClock.id) {
            adjustVisibility()
        }

        if (v?.id == binding.imageRuler.id){
            navigateTo(R.id.convertHomeFragment)
        }

        if (v?.id != binding.imageRuler.id) {
            viewModel.eachClick(v?.id)
        }
    }
}