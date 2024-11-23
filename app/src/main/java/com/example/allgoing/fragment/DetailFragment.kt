package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.allgoing.Adapter.DetailVPAdapter
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentDetailBinding
import com.google.android.material.tabs.TabLayoutMediator

class DetailFragment : Fragment(){
    lateinit var binding: FragmentDetailBinding
    private val information = arrayListOf("홈", "소식", "예약", "리뷰")

    companion object {
        var img1 = ""
        var img2 = ""
        var img3 = ""
        var img4 = ""
        var img5 = ""
    }

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

        binding.detailVp.isUserInputEnabled = false

        for (i in 0 until binding.detailTab.getTabCount()) {
            val tab = (binding.detailTab.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams
            p.setMargins(15, 27, 15, 27)
            tab.requestLayout()
        }

        binding.detailBack.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction().replace(R.id.main_frm, MapFragment()).commitAllowingStateLoss()
        }

        return binding.root
    }

    fun setImg() {
        if (img1 != ""){
            Glide.with(requireContext()).load(img1).into(binding.detailImg1)
        }
        if (img2 != ""){
            Glide.with(requireContext()).load(img2).into(binding.detailImg1)
        }
        if (img3 != ""){
            Glide.with(requireContext()).load(img3).into(binding.detailImg1)
        }
        if (img4 != ""){
            Glide.with(requireContext()).load(img4).into(binding.detailImg1)
        }
        if (img5 != ""){
            Glide.with(requireContext()).load(img5).into(binding.detailImg1)
        }
    }
}