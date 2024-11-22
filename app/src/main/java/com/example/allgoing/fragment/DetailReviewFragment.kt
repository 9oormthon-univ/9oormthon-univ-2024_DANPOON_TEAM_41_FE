package com.example.allgoing.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.Adapter.CommunityRVAdapter
import com.example.allgoing.Adapter.DetailReviewRVAdapter
import com.example.allgoing.activity.CommunityActivity
import com.example.allgoing.databinding.FragmentDetailHomeBinding
import com.example.allgoing.databinding.FragmentDetailReviewBinding
import com.example.allgoing.dataclass.Community
import com.example.allgoing.dataclass.DetailReview

class DetailReviewFragment : Fragment(){
    lateinit var binding: FragmentDetailReviewBinding

    private lateinit var adapter : DetailReviewRVAdapter
    var DetailRecviewDatas = ArrayList<DetailReview>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailReviewBinding.inflate(inflater,container,false)

        initcommunityRecyclerView()

        return binding.root
    }

    fun initcommunityRecyclerView(){
        adapter = DetailReviewRVAdapter()
        adapter.detailreviewlist = DetailRecviewDatas
        adapter.setMyItemClickListener(object : DetailReviewRVAdapter.MyItemClickListener{
            override fun onItemClick(detailReview: DetailReview) {
                val intent = Intent(activity, CommunityActivity::class.java)
                startActivity(intent)
            }
        })

        binding.detailReviewRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun loadSampleData(){
        DetailRecviewDatas.add(DetailReview("다갈꼬양", 4.5f,4.5f,"2020.11.15","맛있",4,5))
        DetailRecviewDatas.add(DetailReview("다안갈꼬양", 1.0f,1.0f,"2020.11.16","오늘 다녀왔어요~ 사장님이 너무 친절하시고 음식도 너무 맛있\n" +
                "었어요 역시 맛집이라 주말에 갔더니 웨이팅이 있어서 30분 정\n" +
                "도 기다리고 먹었습니다. 중앙시장 맛집 인정합니다!",4,5))
        DetailRecviewDatas.add(DetailReview("다꼬양", 2.5f,2.5f,"2020.11.20","오늘 다녀왔어요~ 사장님이 너무 친절하시고 음식도 너무 맛있\n" +
                "었어요 역시 맛집이라 주말에 갔더니 웨이팅이 있어서 30분 정\n" +
                "도 기다리고 먹었습니다. 중앙시장 맛집 인정합니다!",4,5))
    }

}