package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentMypageBinding

class MypageFragment : Fragment(){
    lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater,container,false)
        initSetOnClickListener()

        if (MainActivity.accessToken != "") {
            binding.mypageTitleTv.text = MainActivity.name
            binding.mypageLoginBtnTv.visibility = View.GONE
            binding.mypageEmailTv.visibility = View.VISIBLE
            binding.mypageEmailTv.text = MainActivity.email
        }

        return binding.root
    }

    private fun initSetOnClickListener(){

        //프로필 수정(로그인임)
        binding.mypageLoginBtnTv.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,LoginFragment()).commitAllowingStateLoss()
        }

        //예약 내역
        binding.mypageOption1OrderIb.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,HistoryOrderFragment()).commitAllowingStateLoss()
        }

        //내가 쓴 글
        binding.mypageOption1CommuIb.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,HistoryCommuFragment()).commitAllowingStateLoss()
        }

        //댓글 단 글
        binding.mypageOption1CommentIb.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,HistoryCommentFragment()).commitAllowingStateLoss()
        }

        //좋아요 누른 글
        binding.mypageOption1LikeIb.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,HistoryLikeFragment()).commitAllowingStateLoss()
        }
    }
}