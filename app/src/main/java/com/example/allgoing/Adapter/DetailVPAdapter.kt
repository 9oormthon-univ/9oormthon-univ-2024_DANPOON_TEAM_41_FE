package com.example.allgoing.Adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class DetailVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment){
    override fun getItemCount(): Int = 4

    override fun createFragment(p0: Int): Fragment {
        return when(p0){
            0 ->
            1 ->
            2 ->
            else ->
        }
    }
}