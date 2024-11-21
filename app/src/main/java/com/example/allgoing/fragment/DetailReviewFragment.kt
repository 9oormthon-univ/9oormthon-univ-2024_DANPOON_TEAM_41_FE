package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.databinding.FragmentDetailHomeBinding
import com.example.allgoing.databinding.FragmentDetailReviewBinding

class DetailReviewFragment : Fragment(){
    lateinit var binding: FragmentDetailReviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailReviewBinding.inflate(inflater,container,false)

        return binding.root
    }

}