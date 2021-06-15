package com.hfad.bdcalculator.core.ui.base
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding: ViewBinding>(private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> Binding): Fragment() {

    private var _binding: Binding? = null
    val binding get() = _binding!!

    abstract val viewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        setupLiveData()
        setupUI()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    protected open fun navigateTo(id: Int) {
        findNavController().navigate(id)
    }

    abstract fun setupLiveData()
    abstract fun setupUI()
}