package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentHistoryCommuBinding
import com.example.allgoing.databinding.FragmentHistoryLikeBinding
import com.example.allgoing.databinding.FragmentMapBinding

class HistoryLikeFragment : Fragment(){
    lateinit var binding: FragmentHistoryLikeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryLikeBinding.inflate(inflater,container,false)

        binding.historyLikeBackIv.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageFragment()).commitAllowingStateLoss()
        }
        return binding.root
    }
}