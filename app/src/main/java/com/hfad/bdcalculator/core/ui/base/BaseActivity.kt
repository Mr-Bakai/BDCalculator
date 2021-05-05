package com.example.core.ui.base
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<B: ViewBinding, VM: BaseViewModel>(private var bindingInflate: (LayoutInflater) -> B, className: Class<VM>): AppCompatActivity() {
    private lateinit var binding: B
    private var mViewModel: VM? = null

    val viewModel: VM by lazy {
        ViewModelProvider(this).get(className)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = bindingInflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    abstract fun setUpView()
}