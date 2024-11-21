package com.example.allgoing.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.Adapter.CommunityRVAdapter
import com.example.allgoing.R
import com.example.allgoing.activity.CommunityActivity
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentHistoryCommentBinding
import com.example.allgoing.databinding.FragmentHistoryCommuBinding
import com.example.allgoing.databinding.FragmentMapBinding
import com.example.allgoing.dataclass.Community

class HistoryCommentFragment : Fragment(){
    lateinit var binding: FragmentHistoryCommentBinding

    private lateinit var adapter : CommunityRVAdapter
    var historycommentDatas = ArrayList<Community>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryCommentBinding.inflate(inflater,container,false)

        binding.historyCommentBackIv.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageFragment()).commitAllowingStateLoss()
        }
        inithistorycommentRecyclerView()
        loadSampleData()

        return binding.root
    }

    fun inithistorycommentRecyclerView(){
        adapter = CommunityRVAdapter()
        adapter.communitylist = historycommentDatas
        adapter.setMyItemClickListener(object : CommunityRVAdapter.MyItemClickListener{
            override fun onItemClick(community: Community) {
                val intent = Intent(activity, CommunityActivity::class.java)
                startActivity(intent)
            }
        })
        binding.historyCommentRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun loadSampleData(){
        historycommentDatas.add(Community("이건 댓글 가게",4.5f,"맛있당",16,16))
        historycommentDatas.add(Community("맛없는 가게",5f,"맛있당",1,2,R.drawable.img_food))
        historycommentDatas.add(Community("배고 가게",1f,"맛있당",6,2,R.drawable.img_food))
        historycommentDatas.add(Community("파여 가게",2.5f,"맛있당",1,9,R.drawable.img_food))
        historycommentDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
        historycommentDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
        historycommentDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
    }
}