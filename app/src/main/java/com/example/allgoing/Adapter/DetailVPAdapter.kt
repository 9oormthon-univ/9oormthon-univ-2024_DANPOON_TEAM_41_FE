package com.example.allgoing.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.allgoing.fragment.DetailHomeFragment
import com.example.allgoing.fragment.DetailNoticeFragment
import com.example.allgoing.fragment.DetailOrderFragment
import com.example.allgoing.fragment.DetailReviewFragment

class DetailVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 4

    override fun createFragment(p0: Int): Fragment {
        return when(p0){
            0 -> DetailHomeFragment()
            1 -> DetailNoticeFragment()
            2 -> DetailOrderFragment()
            else -> DetailReviewFragment()
        }
    }
}