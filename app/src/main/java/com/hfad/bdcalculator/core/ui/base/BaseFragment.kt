package com.example.core.ui.base
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<Binding: ViewBinding, VM: BaseViewModel>(private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> Binding, className: Class<VM>): Fragment() {

    private var _binding: Binding? = null
    val binding get() = _binding!!

    val viewModel: VM by lazy {
        ViewModelProvider(this).get(className)
    }

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

    abstract fun setupLiveData()
    abstract fun setupUI()
}