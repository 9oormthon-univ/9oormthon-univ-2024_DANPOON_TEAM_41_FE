package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allgoing.Adapter.HistoryOrderRVAdapter
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentHistoryCommentBinding
import com.example.allgoing.databinding.FragmentHistoryCommuBinding
import com.example.allgoing.databinding.FragmentHistoryOrderBinding
import com.example.allgoing.databinding.FragmentMapBinding
import com.example.allgoing.dataclass.HistoryOrder

class HistoryOrderFragment : Fragment(){

    lateinit var binding: FragmentHistoryOrderBinding
    private lateinit var adapter: HistoryOrderRVAdapter

    private val historyorderList = ArrayList<HistoryOrder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryOrderBinding.inflate(inflater,container,false)

        initPostRecyclerView()
        loadSampleData()

        binding.historyOrderBackIv.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageFragment()).commitAllowingStateLoss()
        }
        return binding.root
    }

    private fun initPostRecyclerView() {
        adapter = HistoryOrderRVAdapter(historyorderList)
        binding.historyOrderRv.adapter = adapter
        binding.historyOrderRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun loadSampleData() {
        // 샘플 데이터 로드 (임시 데이터)
        historyorderList.add(HistoryOrder("고양이 가게", "2024.10.11", "참치, 츄르"))
        historyorderList.add(HistoryOrder("고양이네 생선가게", "2024.10.13", "갈치,고등어 외2. . ."))
        historyorderList.add(HistoryOrder("강아지 가게", "2024.10.11", "참치, 츄르"))
        historyorderList.add(HistoryOrder("고양이 가게", "2024.10.11", "참치, 츄르"))
        historyorderList.add(HistoryOrder("고양이 가게", "2024.10.11", "참치, 츄르"))
        historyorderList.add(HistoryOrder("고양이 가게", "2024.10.11", "참치, 츄르"))
    }

}