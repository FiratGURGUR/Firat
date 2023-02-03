package com.app.firat.gurgurfirat.detail

import androidx.navigation.fragment.navArgs
import com.app.firat.gurgurfirat.base.BaseFragment
import com.app.firat.gurgurfirat.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

    private val args : DetailFragmentArgs by navArgs()

    override fun initView() {
        with(binding){
            tv.text = args.id.toString()
        }
    }

    override fun initObservers() {

    }

}