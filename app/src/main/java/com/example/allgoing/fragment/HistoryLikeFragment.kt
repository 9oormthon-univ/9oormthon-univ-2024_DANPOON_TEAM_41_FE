package com.example.allgoing.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.Adapter.HistoryCommuRVAdapter
import com.example.allgoing.R
import com.example.allgoing.activity.CommunityActivity
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentHistoryCommuBinding
import com.example.allgoing.databinding.FragmentHistoryLikeBinding
import com.example.allgoing.databinding.FragmentMapBinding
import com.example.allgoing.dataclass.HistoryCommu

class HistoryLikeFragment : Fragment(){
    lateinit var binding: FragmentHistoryLikeBinding

    private lateinit var adapter : HistoryCommuRVAdapter
    var HistoryLikeDatas = ArrayList<HistoryCommu>()

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
        inithistorylikerecyclerView()
        loadSampleData()

        return binding.root
    }

    fun inithistorylikerecyclerView(){
        adapter = HistoryCommuRVAdapter()
        adapter.historycommulist = HistoryLikeDatas
        adapter.setMyItemClickListener(object : HistoryCommuRVAdapter.MyItemClickListener{
            override fun onItemClick(historyCommu: HistoryCommu) {
                val intent = Intent(activity, CommunityActivity::class.java)
                startActivity(intent)
            }
        })
        binding.historyLikeRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }
    private fun loadSampleData(){
        HistoryLikeDatas.add(HistoryCommu("이건 좋아요 가게 가게","맛있당",16,16))
        HistoryLikeDatas.add(HistoryCommu("맛없는 가게","맛있당",1,2,R.drawable.img_food))
        HistoryLikeDatas.add(HistoryCommu("배고 가게","맛있당",6,2,R.drawable.img_food))
        HistoryLikeDatas.add(HistoryCommu("파여 가게","맛있당",1,9,R.drawable.img_food))
        HistoryLikeDatas.add(HistoryCommu("맛있는 가게","맛있당",1,2,R.drawable.img_food))
        HistoryLikeDatas.add(HistoryCommu("맛있는 가게","맛있당",1,2,R.drawable.img_food))
        HistoryLikeDatas.add(HistoryCommu("맛있는 가게","맛있당",1,2,R.drawable.img_food))
    }
}