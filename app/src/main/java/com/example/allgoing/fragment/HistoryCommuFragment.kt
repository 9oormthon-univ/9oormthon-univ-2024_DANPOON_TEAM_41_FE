package com.example.allgoing.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.allgoing.Adapter.HistoryCommuRVAdapter
import com.example.allgoing.R
import com.example.allgoing.activity.CommunityActivity
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentHistoryCommuBinding
import com.example.allgoing.databinding.FragmentMapBinding
import com.example.allgoing.dataclass.Community
import com.example.allgoing.dataclass.HistoryCommu

class HistoryCommuFragment : Fragment(){
    lateinit var binding: FragmentHistoryCommuBinding

    private lateinit var adapter : HistoryCommuRVAdapter
    var HistoryCommuDatas = ArrayList<HistoryCommu>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryCommuBinding.inflate(inflater,container,false)

        binding.historyCommuBackIv.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageFragment()).commitAllowingStateLoss()
        }
        inithistorycommuRecyclerView()
        loadSampleData()

        setSelectedTab(binding.historyCommuReviewIb, binding.historyCommuReviewTv)

        binding.historyCommuReviewIb.setOnClickListener{
            setSelectedTab(binding.historyCommuReviewIb, binding.historyCommuReviewTv)
        }
        binding.historyCommuQnaIb.setOnClickListener{
            setSelectedTab(binding.historyCommuQnaIb, binding.historyCommuQnaTv)
        }

        return binding.root
    }

    private fun setSelectedTab(selectedButton: ImageButton, selectedText: TextView){
        historycommuTabSelection()

        selectedButton.isSelected = true
        selectedText.isSelected = true
    }

    fun inithistorycommuRecyclerView(){
        adapter = HistoryCommuRVAdapter()
        adapter.historycommulist = HistoryCommuDatas
        adapter.setMyItemClickListener(object : HistoryCommuRVAdapter.MyItemClickListener{
            override fun onItemClick(historyCommu: HistoryCommu) {
                val intent = Intent(activity, CommunityActivity::class.java)
                startActivity(intent)
            }
        })
        binding.historyCommuRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun historycommuTabSelection(){
        binding.historyCommuReviewIb.isSelected = false
        binding.historyCommuReviewTv.isSelected = false
        binding.historyCommuQnaIb.isSelected = false
        binding.historyCommuQnaTv.isSelected = false
    }
    private fun loadSampleData(){
        HistoryCommuDatas.add(HistoryCommu("이건 쓴 글 가게","맛있당",16,16))
        HistoryCommuDatas.add(HistoryCommu("맛없는 가게","맛있당",1,2,R.drawable.img_food))
        HistoryCommuDatas.add(HistoryCommu("배고 가게","맛있당",6,2,R.drawable.img_food))
        HistoryCommuDatas.add(HistoryCommu("파여 가게","맛있당",1,9,R.drawable.img_food))
        HistoryCommuDatas.add(HistoryCommu("맛있는 가게","맛있당",1,2,R.drawable.img_food))
        HistoryCommuDatas.add(HistoryCommu("맛있는 가게","맛있당",1,2,R.drawable.img_food))
        HistoryCommuDatas.add(HistoryCommu("맛있는 가게","맛있당",1,2,R.drawable.img_food))
    }
}