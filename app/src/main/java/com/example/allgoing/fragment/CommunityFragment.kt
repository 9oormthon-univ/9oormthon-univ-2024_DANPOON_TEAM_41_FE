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
import com.example.allgoing.databinding.FragmentCommunityBinding
import com.example.allgoing.dataclass.Community

class CommunityFragment : Fragment(){
    lateinit var binding: FragmentCommunityBinding

    private lateinit var adpater : CommunityRVAdapter
    var CommunityDatas = ArrayList<Community>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater,container,false)

        loadSampleData()
        initcommunityRecyclerView()

        return binding.root
    }

    fun initcommunityRecyclerView(){
        adpater = CommunityRVAdapter()
        adpater.communitylist = CommunityDatas
        adpater.setMyItemClickListener(object : CommunityRVAdapter.MyItemClickListener{
            override fun onItemClick(community: Community) {
                val intent = Intent(activity, CommunityActivity::class.java)
                startActivity(intent)
            }
        })

        binding.communityRv.adapter = adpater
        adpater.notifyDataSetChanged()
    }
    private fun loadSampleData(){
        CommunityDatas.add(Community("맛있는 가게",4.5f,"맛있당",16,16))
        CommunityDatas.add(Community("맛없는 가게",5f,"맛있당",1,2,R.drawable.img_food))
        CommunityDatas.add(Community("배고 가게",1f,"맛있당",6,2,R.drawable.img_food))
        CommunityDatas.add(Community("파여 가게",2.5f,"맛있당",1,9,R.drawable.img_food))
        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
    }
}