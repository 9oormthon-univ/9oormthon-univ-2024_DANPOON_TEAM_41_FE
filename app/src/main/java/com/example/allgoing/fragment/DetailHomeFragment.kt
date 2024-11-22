package com.example.allgoing.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allgoing.Adapter.DetailHomeImgRVAdapter
import com.example.allgoing.Adapter.DetailHomeMenuRVAdapter
import com.example.allgoing.Adapter.HistoryOrderRVAdapter
import com.example.allgoing.R
import com.example.allgoing.databinding.FragmentDetailHomeBinding
import com.example.allgoing.dataclass.DetailHomeImg
import com.example.allgoing.dataclass.DetailHomeMenu
import com.example.allgoing.dataclass.HistoryOrder

class DetailHomeFragment : Fragment(){
    lateinit var binding: FragmentDetailHomeBinding

    private lateinit var menuadapter: DetailHomeMenuRVAdapter
    private lateinit var imgadapter: DetailHomeImgRVAdapter

    private val detailhomemenuList = ArrayList<DetailHomeMenu>()
    private val detailhomeimgList = ArrayList<DetailHomeImg>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailHomeBinding.inflate(inflater,container,false)


        binding.detailHomeDropLl.setOnClickListener{
            toggleDetailView()
        }


        loadMenuData()
        initMenuRecyclerView()
        initImgRecyclerView()
        return binding.root
    }

    private fun toggleDetailView() {
        // 토글 상태에 따라 요일 레이아웃을 보여주거나 숨기기
        if (!binding.detailHomeTimeDetailLl.isSelected) {
            binding.detailHomeAllTimeTv.visibility = View.VISIBLE
            binding.detailHomeDropIv.setImageResource(R.drawable.ic_toggle_up) // 아이콘 변경
        } else {
            binding.detailHomeAllTimeTv.visibility = View.GONE
            binding.detailHomeDropIv.setImageResource(R.drawable.ic_drop) // 기본 아이콘으로 복원
        }
        // 선택 상태 변경
        binding.detailHomeTimeDetailLl.isSelected = !binding.detailHomeTimeDetailLl.isSelected
    }


    private fun initMenuRecyclerView() {
        menuadapter = DetailHomeMenuRVAdapter(detailhomemenuList)
        binding.detailHomeItemRv.adapter = menuadapter
        binding.detailHomeItemRv.layoutManager = GridLayoutManager(context,3)
    }

    private fun initImgRecyclerView() {
        imgadapter = DetailHomeImgRVAdapter(detailhomeimgList)
        binding.detailHomePicRv.adapter = imgadapter
        binding.detailHomePicRv.layoutManager = GridLayoutManager(context,3)
    }

    private fun loadMenuData() {
        detailhomemenuList.add(DetailHomeMenu("떡볶이","3000원", R.drawable.img_food))
        detailhomemenuList.add(DetailHomeMenu("떡볶이","3000원", R.drawable.img_food))
        detailhomemenuList.add(DetailHomeMenu("떡볶이","3000원", R.drawable.img_food))
        detailhomemenuList.add(DetailHomeMenu("떡볶이","3000원", R.drawable.img_food))
        detailhomemenuList.add(DetailHomeMenu("떡볶이","3000원", R.drawable.img_food))
        detailhomemenuList.add(DetailHomeMenu("떡볶이","3000원", R.drawable.img_food))
        detailhomemenuList.add(DetailHomeMenu("떡볶이","3000원", R.drawable.img_food))

        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food))
    }



}