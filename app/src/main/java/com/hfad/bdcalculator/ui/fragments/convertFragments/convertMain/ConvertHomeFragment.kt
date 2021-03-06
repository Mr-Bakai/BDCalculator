package com.hfad.bdcalculator.ui.fragments.convertFragments.convertMain

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.view.View
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.hfad.bdcalculator.R
import com.hfad.bdcalculator.core.ui.base.BaseFragment
import com.hfad.bdcalculator.databinding.FragmentConvertHomeBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class ConvertHomeFragment :
    BaseFragment<FragmentConvertHomeBinding>(FragmentConvertHomeBinding::inflate),
    View.OnClickListener {

    override val viewModel: ConvertHomeViewModel by viewModel()

    private lateinit var viewPager: ViewPager2

    override fun setupLiveData() {}

    override fun setupUI() {
        setViewPager()
        setListeners()
    }

    private fun setListeners() {
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
        binding.textArrowUp.setAnimation()
        binding.textArrowDown.setAnimation()
        binding.backspace.setAnimation()
        binding.plusMinus.setAnimation()
        binding.comma.setAnimation()
        binding.c.setAnimation()
    }

    private fun setViewPager() {
        viewPager = binding.viewPager
        viewPager.adapter = FragmentAdapter(this, viewModel.clickTypeLiveData)

        TabLayoutMediator(binding.tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.setCustomView(R.layout.area_view)
                1 -> tab.setCustomView(R.layout.length_view)
                2 -> tab.setCustomView(R.layout.temperature_view)
                3 -> tab.setCustomView(R.layout.volume_view)
                4 -> tab.setCustomView(R.layout.mass_view)
                5 -> tab.setCustomView(R.layout.data_view)
                6 -> tab.setCustomView(R.layout.speed_view)
            }
        }.attach()


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.customView?.id) {
                    R.id.areaView -> tab.parent?.findViewById<TextView>(R.id.textTitleArea)
                        ?.setTextColor(resources.getColor(android.R.color.black))

                    R.id.lengthView -> tab.parent?.findViewById<TextView>(R.id.textTitleLength)
                        ?.setTextColor(resources.getColor(android.R.color.black))

                    R.id.temperatureView -> tab.parent?.findViewById<TextView>(R.id.textTitleTemperature)
                        ?.setTextColor(resources.getColor(android.R.color.black))

                    R.id.volumeView -> tab.parent?.findViewById<TextView>(R.id.textTitleVolume)
                        ?.setTextColor(resources.getColor(android.R.color.black))

                    R.id.massView -> tab.parent?.findViewById<TextView>(R.id.textTitleMass)
                        ?.setTextColor(resources.getColor(android.R.color.black))

                    R.id.dataView -> tab.parent?.findViewById<TextView>(R.id.textTitleData)
                        ?.setTextColor(resources.getColor(android.R.color.black))

                    R.id.speedView -> tab.parent?.findViewById<TextView>(R.id.textTitleSpeed)
                        ?.setTextColor(resources.getColor(android.R.color.black))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                when (tab?.customView?.id) {
                    R.id.areaView -> tab.parent?.findViewById<TextView>(R.id.textTitleArea)
                        ?.setTextColor(resources.getColor(android.R.color.darker_gray))

                    R.id.lengthView -> tab.parent?.findViewById<TextView>(R.id.textTitleLength)
                        ?.setTextColor(resources.getColor(android.R.color.darker_gray))

                    R.id.temperatureView -> tab.parent?.findViewById<TextView>(R.id.textTitleTemperature)
                        ?.setTextColor(resources.getColor(android.R.color.darker_gray))

                    R.id.volumeView -> tab.parent?.findViewById<TextView>(R.id.textTitleVolume)
                        ?.setTextColor(resources.getColor(android.R.color.darker_gray))

                    R.id.massView -> tab.parent?.findViewById<TextView>(R.id.textTitleMass)
                        ?.setTextColor(resources.getColor(android.R.color.darker_gray))

                    R.id.dataView -> tab.parent?.findViewById<TextView>(R.id.textTitleData)
                        ?.setTextColor(resources.getColor(android.R.color.darker_gray))

                    R.id.speedView -> tab.parent?.findViewById<TextView>(R.id.textTitleSpeed)
                        ?.setTextColor(resources.getColor(android.R.color.darker_gray))
                }
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun TextView.setAnimation() {
        this.setOnClickListener {

            val startSize = 26f
            val endSize = 15f

            val colorFrom = resources.getColor(R.color.blackish)
            val colorTo = resources.getColor(R.color.whitish)

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
}