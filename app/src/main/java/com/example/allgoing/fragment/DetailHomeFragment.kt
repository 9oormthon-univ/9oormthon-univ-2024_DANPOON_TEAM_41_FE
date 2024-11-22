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

//        binding.detailHomeDropLl.isClickable = true // 클릭 가능
//        binding.detailHomeDropLl.isFocusable = true // 포커스 가능

        binding.detailHomeDropLl.setOnClickListener{
            toggleDetailView()
        }

//        binding.detailHomeDropLl.setOnTouchListener { _, event ->
//            when (event.action) {
//                MotionEvent.ACTION_DOWN -> { true }
//                MotionEvent.ACTION_UP -> { toggleDetailView()
//                    true }
//                else -> false
//            }
//        }

        loadMenuData()
        initMenuRecyclerView()
        initImgRecyclerView()
        return binding.root
    }

    private fun toggleDetailView(){
//        Log.d("click test","test")
        if (!binding.detailHomeTimeDetailLl.isSelected){
            binding.detailHomeAllTimeTv.visibility = View.VISIBLE
        } else {
            binding.detailHomeAllTimeTv.visibility = View.GONE
        }
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