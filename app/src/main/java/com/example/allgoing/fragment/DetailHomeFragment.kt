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
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentDetailHomeBinding
import com.example.allgoing.dataclass.DetailHomeImg
import com.example.allgoing.dataclass.DetailHomeMenu
import com.example.allgoing.dataclass.HistoryOrder
import com.example.allgoing.retrofit.DTO.Response.StoreHomeRes
import com.example.allgoing.retrofit.DTO.Response.TraditionalListRes
import com.example.allgoing.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailHomeFragment : Fragment(){
    lateinit var binding: FragmentDetailHomeBinding

    private lateinit var menuadapter: DetailHomeMenuRVAdapter
    private lateinit var imgadapter: DetailHomeImgRVAdapter

    private val detailhomemenuList = ArrayList<DetailHomeMenu>()
    private val detailhomeimgList = ArrayList<DetailHomeImg>()

    var storeId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailHomeBinding.inflate(inflater,container,false)


//        getStoreHome(storeId)
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

    private fun getStoreHome(storeId: Int) {
        RetrofitClient.service.getStoreHome(storeId).enqueue(object : Callback<StoreHomeRes> {
            override fun onResponse(call: Call<StoreHomeRes>, response: Response<StoreHomeRes>) {
                if (response.isSuccessful) {
                    response.body()?.let { storeHome ->
                        updateUI(storeHome.information)
                    } ?: Log.e("DetailHomeFragment", "Response body is null")
                } else {
                    Log.e("DetailHomeFragment", "Failed response: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<StoreHomeRes>, t: Throwable) {
                Log.e("DetailHomeFragment", "Error: ${t.message}")
            }
        })
    }

    private fun updateUI(information: StoreHomeRes.Information) {
        // UI 데이터 업데이트
        binding.detialHomeAddressTv.text = information.storeAddress
        binding.detailHomeCallTv.text = information.storePhone
        binding.detailHomeIsopenTv.text = if (information.storeInfos.any { it.open }) "영업중" else "영업종료"

        // 영업 시간
        val openTimes = information.storeInfos.joinToString("\n") {
            "${it.day} ${it.openTime}~${it.closeTime}"
        }
        binding.detailHomeAllTimeTv.text = openTimes

        // 메뉴 데이터 업데이트
        detailhomemenuList.clear()
        detailhomemenuList.addAll(information.products.map {
            DetailHomeMenu(
                detail_home_item = it.productName,
                detail_home_price = "${it.productPrice}원",
                detail_home_menu_Img = null // Glide 등으로 처리 가능
            )
        })
        menuadapter.notifyDataSetChanged()

        // 이미지 데이터 업데이트
        detailhomeimgList.clear()
        detailhomeimgList.addAll(information.storeImages.map {
            DetailHomeImg(detial_home_Img = null) // URL 이미지 처리 필요
        })
        imgadapter.notifyDataSetChanged()
    }
        private fun loadMenuData() {
        detailhomemenuList.add(DetailHomeMenu("떡볶이","3000원", R.drawable.img_food2))
        detailhomemenuList.add(DetailHomeMenu("튀김","3000원", R.drawable.img_food3))
        detailhomemenuList.add(DetailHomeMenu("순대","3000원", R.drawable.img_food))
        detailhomemenuList.add(DetailHomeMenu("어묵","3000원", R.drawable.img_food5))
        detailhomemenuList.add(DetailHomeMenu("우동","3000원", R.drawable.img_food6))
        detailhomemenuList.add(DetailHomeMenu("슬러시","3000원", R.drawable.img_food7))


        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food2))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food2))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food3))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food7))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food5))
        detailhomeimgList.add(DetailHomeImg(R.drawable.img_food6))
    }

}
