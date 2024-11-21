package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentEditProfileBinding
import com.example.allgoing.databinding.FragmentWriteCommuBinding
import com.example.allgoing.databinding.FragmentWriteReviewBinding

class WriteReviewFragment : Fragment(){
    lateinit var binding: FragmentWriteReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteReviewBinding.inflate(inflater,container,false)

        binding.writeReviewBackIv.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,CommunityFragment()).commitAllowingStateLoss()
        }

        return binding.root
    }
}