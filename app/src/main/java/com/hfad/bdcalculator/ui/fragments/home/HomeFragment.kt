package com.hfad.bdcalculator.ui.fragments.home

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.example.core.ui.base.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hfad.bdcalculator.R
import com.hfad.bdcalculator.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class.java
), View.OnClickListener {
    override fun setupLiveData() {}

    override fun setupUI() {
        removeToolbarBottomBar()
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
        binding.imageClock.setOnClickListener{adjustVisibility()}
        observers()
    }

    private fun observers() {
        viewModel.typedLiveData.observe(viewLifecycleOwner) {
            binding.textMain.setText(it)
            binding.textMain.setSelection(binding.textMain.text.length)
        }

        viewModel.resultLiveData.observe(viewLifecycleOwner) {
            binding.textResult.text = it.toString()
        }
    }

    private fun adjustVisibility(){
        binding.historyContainer.isVisible = !binding.historyContainer.isVisible
    }

    private fun TextView.setAnimation() {
        this.setOnClickListener {

            var startSize = 26f
            val endSize = 15f

            val colorFrom = resources.getColor(R.color.blackish)
            var colorTo = resources.getColor(R.color.whitish)

            if (it.id == binding.addition.id
                || it.id == binding.minus.id
                || it.id == binding.div.id)              startSize = 40f

             else if(it.id == binding.multiply.id)       startSize = 30f
             else if(it.id == binding.parentheses.id)    startSize = 22f
             else if(it.id == binding.equal.id){         startSize = 37f; colorTo = resources.getColor(R.color.green)}


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

    override fun onClick(v: View?) {
        viewModel.eachClick(v?.id)
    }

    private fun removeToolbarBottomBar() {
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navBar.visibility = View.GONE
    }
}