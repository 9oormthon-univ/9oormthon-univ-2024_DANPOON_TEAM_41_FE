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

class WriteCommuFragment : Fragment(){
    lateinit var binding: FragmentWriteCommuBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWriteCommuBinding.inflate(inflater,container,false)

        binding.writeCommuBackIv.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,CommunityFragment()).commitAllowingStateLoss()
        }

        return binding.root
    }
}