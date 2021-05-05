package com.hfad.bdcalculator.ui.fragments.home
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.core.ui.base.BaseFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hfad.bdcalculator.R
import com.hfad.bdcalculator.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    FragmentHomeBinding::inflate,
    HomeViewModel::class.java
), View.OnClickListener{
    override fun setupLiveData(){}

    override fun setupUI(){
        removeToolbarBottomBar()
        binding.one.setOnClickListener(this)
        binding.two.setOnClickListener(this)
        binding.three.setOnClickListener(this)
        binding.four.setOnClickListener(this)
        binding.five.setOnClickListener(this)
        binding.six.setOnClickListener(this)
        binding.seven.setOnClickListener(this)
        binding.eight.setOnClickListener(this)
        binding.nine.setOnClickListener(this)
        binding.parentheses.setOnClickListener(this)
        binding.percentage.setOnClickListener(this)
        binding.div.setOnClickListener(this)
        binding.multiply.setOnClickListener(this)
        binding.minus.setOnClickListener(this)
        binding.addition.setOnClickListener(this)
        binding.equal.setOnClickListener(this)
        binding.c.setOnClickListener(this)
        observers()
    }

    private fun observers() {
        viewModel.fieldLiveData.observe(this) {
            binding.textMain.setText(it)
            binding.textMain.setSelection(binding.textMain.text.length)
        }
    }

    override fun onClick(v: View?) {
        viewModel.eachClick(v?.id)
    }

    private fun removeToolbarBottomBar() {
        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.nav_view)
        navBar.visibility = View.GONE
        (activity as AppCompatActivity).supportActionBar?.hide()
    }
}