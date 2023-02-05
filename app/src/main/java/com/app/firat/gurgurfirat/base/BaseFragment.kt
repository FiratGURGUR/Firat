package com.app.firat.gurgurfirat.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.app.firat.gurgurfirat.ui.MainActivity
import com.app.firat.gurgurfirat.util.UtilDialog


typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<viewBinding : ViewBinding>(private val inflate: Inflate<viewBinding>) :
    Fragment() {


    private var _binding: viewBinding? = null
    val binding get() = _binding!!

    private val progressDialog: Dialog by lazy { UtilDialog.setProgressDialog(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().runOnUiThread {
            initView()
        }
        initObservers()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun initView()

    abstract fun initObservers()

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected fun showLoading(isShown: Boolean) {
        if (isShown) showProgressDialog()
        else hideProgressDialog()
    }

    private fun showProgressDialog() {
        hideProgressDialog()
        progressDialog.show()
    }

    private fun hideProgressDialog() {
        if (progressDialog.isShowing) progressDialog.cancel()
    }

    fun getMainActivity(): MainActivity = activity as MainActivity



}