package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.Adapter.DetailVPAdapter
import com.example.allgoing.databinding.FragmentDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment(){
    lateinit var binding: FragmentDetailBinding
    private val information = arrayListOf("홈", "소식", "예약", "리뷰")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater,container,false)

        val vpAdapter = DetailVPAdapter(this)
        binding.detailVp.adapter = vpAdapter

        TabLayoutMediator(binding.detailTab, binding.detailVp){
                tab, p0 ->
            tab.text = information[p0]
        }.attach()

        for (i in 0 until binding.detailTab.getTabCount()) {
            val tab = (binding.detailTab.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(15, 27, 15, 27)
            tab.requestLayout()
        }

        return binding.root
    }
}