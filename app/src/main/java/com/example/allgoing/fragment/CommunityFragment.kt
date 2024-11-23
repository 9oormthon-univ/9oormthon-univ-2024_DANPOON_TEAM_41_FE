package com.example.allgoing.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.allgoing.Adapter.CommunityRVAdapter
import com.example.allgoing.R
import com.example.allgoing.activity.CommunityActivity
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentCommunityBinding
import com.example.allgoing.retrofit.DTO.Response.ReviewTraditionalRes
import com.example.allgoing.retrofit.RetrofitClient
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityFragment : Fragment(){
    lateinit var binding: FragmentCommunityBinding
    lateinit var addbutton: ExtendedFloatingActionButton
    lateinit var writebutton: ExtendedFloatingActionButton
    lateinit var reviewbutton: ExtendedFloatingActionButton
    var isAllbuttonVisble = false

    private lateinit var adapter : CommunityRVAdapter
    var CommunityDatas = ArrayList<ReviewTraditionalRes.Community>()
    var traditionalId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater,container,false)

//        loadSampleData()
        getReviewTraditional(traditionalId)
        initcommunityRecyclerView()

        //탭 색 바꾸기
        setSelectedTab(binding.communityReviewIb, binding.communityReviewTv)

        binding.communityReviewIb.setOnClickListener{
            setSelectedTab(binding.communityReviewIb, binding.communityReviewTv)
        }
        binding.communityQnaIb.setOnClickListener{
            setSelectedTab(binding.communityQnaIb,binding.communityQnaTv)
        }

        //플로팅 버튼 초기화
        addbutton = binding.communityFloatPlusBtn
        writebutton = binding.communityFloatCommuWriteBtn
        reviewbutton = binding.communityFloatReviewWriteBtn

        writebutton.visibility = View.GONE
        reviewbutton.visibility = View.GONE

        addbutton.setOnClickListener{
            toggleFloatingButtons()
        }

        writebutton.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,WriteCommuFragment()).commitAllowingStateLoss()
        }
        reviewbutton.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,WriteReviewFragment()).commitAllowingStateLoss()
        }


        return binding.root
    }

    // Retrofit API 응답 처리
    private fun getReviewTraditional(traditionalId: Int) {
        RetrofitClient.service.getReviewTraditional(MainActivity.accessToken, traditionalId)
            .enqueue(object : Callback<ReviewTraditionalRes> {
                override fun onFailure(call: Call<ReviewTraditionalRes>?, t: Throwable?) {
                    Log.e("retrofit", t.toString())
                }

                override fun onResponse(
                    call: Call<ReviewTraditionalRes>?,
                    response: Response<ReviewTraditionalRes>?
                ) {
                    Log.d("retrofit", response.toString())
                    Log.d("retrofit", response?.code().toString())
                    Log.d("retrofit", response?.message().toString())

                    if (response != null && response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            val communityList = responseBody.information ?: ArrayList()

                            // RecyclerView 데이터 세팅
                            if (communityList.isNotEmpty()) {
                                adapter.communitylist.clear() // 기존 데이터를 초기화
                                adapter.communitylist.addAll(communityList) // 새로운 데이터 추가
                                adapter.notifyDataSetChanged() // RecyclerView 갱신
                                initcommunityRecyclerView()
                            } else {
                                Log.e("retrofit", "Community list is empty.")
                            }
                        } else {
                            Log.e("retrofit", "Response body is null.")
                        }
                    } else {
                        Log.e(
                            "retrofit",
                            "Response failed or is null: ${response?.errorBody()?.string()}"
                        )
                    }
                }
            })
    }



    private fun setSelectedTab(selectedButton: ImageButton, selectedText: TextView){
        communityTabSelection()

        selectedButton.isSelected = true
        selectedText.isSelected = true
    }

    private fun communityTabSelection(){
        binding.communityReviewIb.isSelected = false
        binding.communityReviewTv.isSelected = false
        binding.communityQnaIb.isSelected = false
        binding.communityQnaTv.isSelected = false
    }

    private fun toggleFloatingButtons(){
        if(!isAllbuttonVisble){
            writebutton.visibility = View.VISIBLE
            reviewbutton.visibility = View.VISIBLE
            rotateAddButton(45f)
        }else {
            writebutton.visibility = View.GONE
            reviewbutton.visibility = View.GONE
            rotateAddButton(0f)
        }
        isAllbuttonVisble = !isAllbuttonVisble
    }

    private fun rotateAddButton(targetRotation: Float) {
        addbutton.animate()
            .rotation(targetRotation) // 원하는 각도로 회전
            .setDuration(300) // 애니메이션 지속 시간
            .setInterpolator(LinearInterpolator()) // 선형 속도
            .start()
    }

    fun initcommunityRecyclerView(){
        adapter = CommunityRVAdapter()
        adapter.communitylist = CommunityDatas
        adapter.setMyItemClickListener(object : CommunityRVAdapter.MyItemClickListener{
            override fun onItemClick(community: ReviewTraditionalRes.Community) {
                val intent = Intent(activity, CommunityActivity::class.java)
                startActivity(intent)
            }
        })

        binding.communityRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }
//    private fun loadSampleData(){
//        CommunityDatas.add(Community("이건 커뮤니티 가게",4.5f,"맛있당",16,16))
//        CommunityDatas.add(Community("맛없는 가게",5f,"맛있당",1,2,R.drawable.img_food))
//        CommunityDatas.add(Community("배고 가게",1f,"맛있당",6,2,R.drawable.img_food))
//        CommunityDatas.add(Community("파여 가게",2.5f,"맛있당",1,9,R.drawable.img_food))
//        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
//        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
//        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
//    }
}