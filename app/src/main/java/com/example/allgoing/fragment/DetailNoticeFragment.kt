package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allgoing.Adapter.DetailNoticeRVAdapter
import com.example.allgoing.databinding.FragmentDetailNoticeBinding

class DetailNoticeFragment : Fragment(){
    lateinit var binding: FragmentDetailNoticeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailNoticeBinding.inflate(inflater,container,false)

        initRV()

        return binding.root
    }

    private fun initRV() {

        val detailNoticeRVAdapter = DetailNoticeRVAdapter()

        detailNoticeRVAdapter.setClickListener(object : DetailNoticeRVAdapter.MyClickListener{
            override fun itemSelect(posion:Int) {

                initRV()
            }
        })

        binding.detailNoticeNotice.adapter = detailNoticeRVAdapter
        binding.detailNoticeNotice.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

}