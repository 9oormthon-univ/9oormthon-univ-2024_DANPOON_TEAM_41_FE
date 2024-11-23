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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allgoing.Adapter.CommunityRVAdapter
import com.example.allgoing.Adapter.SelecTraditionalRVAdapter
import com.example.allgoing.Adapter.SelectShopRVAdapter
import com.example.allgoing.R
import com.example.allgoing.activity.CommunityActivity
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentCommunityBinding
import com.example.allgoing.retrofit.DTO.DataClass.Board
import com.example.allgoing.retrofit.DTO.DataClass.Review
import com.example.allgoing.retrofit.DTO.Response.BoardListRes
import com.example.allgoing.retrofit.DTO.Response.ReviewTraditionalRes
import com.example.allgoing.retrofit.RetrofitClient
import com.example.allgoing.databinding.ItemSelectTraditionalBinding
import com.example.allgoing.dataclass.Community
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CommunityFragment : Fragment(){
    lateinit var binding: FragmentCommunityBinding
    lateinit var addbutton: ExtendedFloatingActionButton
    private lateinit var adapter: CommunityRVAdapter
    private var communityDatas = ArrayList<Review>()
    private var boardDatas = ArrayList<Board>()

    private var isReviewTabSelected = true // 현재 선택된 탭 상태를 저장
    var traditionalId: Int = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommunityBinding.inflate(inflater, container, false)

        // 초기 탭 설정
        setSelectedTab(binding.communityReviewIb, binding.communityReviewTv)
        getReviewTraditional(traditionalId)

        // RecyclerView 초기화
        initRecyclerView()

        // 리뷰 탭 클릭 이벤트
        binding.communityReviewIb.setOnClickListener {
            setSelectedTab(binding.communityReviewIb, binding.communityReviewTv)
            isReviewTabSelected = true
            getReviewTraditional(traditionalId) // 리뷰 데이터 가져오기
        }

        // 게시판 탭 클릭 이벤트
        binding.communityQnaIb.setOnClickListener {
            setSelectedTab(binding.communityQnaIb, binding.communityQnaTv)
            isReviewTabSelected = false
            getBoardList() // 게시판 데이터 가져오기
        }

        writebutton.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,WriteCommuFragment()).commitAllowingStateLoss()
        }
        reviewbutton.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,WriteReviewFragment()).commitAllowingStateLoss()
        }

        binding.communitySelectShopLayout.setOnClickListener{
            binding.communitySelectTraditionalRv.visibility = View.VISIBLE
            binding.communityLocalDropIv.setImageResource(R.drawable.ic_toggle_up)
            initRV()
        }


        return binding.root
    }

    // 리뷰 데이터 가져오기
    private fun getReviewTraditional(traditionalId: Int) {
        RetrofitClient.service.getReviewTraditional(MainActivity.accessToken, traditionalId)
            .enqueue(object : Callback<ReviewTraditionalRes> {
                override fun onFailure(call: Call<ReviewTraditionalRes>, t: Throwable) {
                    Log.e("retrofit", t.toString())
                }

                override fun onResponse(
                    call: Call<ReviewTraditionalRes>,
                    response: Response<ReviewTraditionalRes>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            communityDatas.clear()
                            communityDatas.addAll(responseBody.information ?: ArrayList())
                            updateRecyclerView()
                        }
                    } else {
                        Log.e("retrofit", "Failed to load reviews: ${response.errorBody()?.string()}")
                    }
                }
            })
    }

    // 게시판 데이터 가져오기
    private fun getBoardList() {
        RetrofitClient.service.getBoardList(MainActivity.accessToken)
            .enqueue(object : Callback<BoardListRes> {
                override fun onFailure(call: Call<BoardListRes>, t: Throwable) {
                    Log.e("retrofit", t.toString())
                }

                override fun onResponse(
                    call: Call<BoardListRes>,
                    response: Response<BoardListRes>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            boardDatas.clear()
                            boardDatas.addAll(responseBody.information ?: ArrayList())
                            updateRecyclerView()
                        }
                    } else {
                        Log.e("retrofit", "Failed to load boards: ${response.errorBody()?.string()}")
                    }
                }
            })
    }

    // RecyclerView 업데이트
    private fun updateRecyclerView() {
        if (isReviewTabSelected) {
            adapter.communitylist.clear()
            adapter.communitylist.addAll(communityDatas)
        } else {
            adapter.communitylist.clear()
            boardDatas.forEach { board ->
                // 게시판 데이터를 Community 형식으로 매핑
                val fakeCommunity = Review(
                    reviewId = board.postId,
                    reviewTitle = board.title,
                    reviewContent = board.content,
                    likeCount = board.likeCount,
                    writerName = "",
                    reviewImages = ArrayList(),
                    liked = board.isLiked,
                    createdAt = board.createdAt,
                    star = 0,
                    userId = 0,
                    storeId = 0
                )
                adapter.communitylist.add(fakeCommunity)
            }
        }
        adapter.notifyDataSetChanged()
    }

    // RecyclerView 초기화
    private fun initRecyclerView() {
        adapter = CommunityRVAdapter()
        binding.communityRv.adapter = adapter

        adapter.setMyItemClickListener(object : CommunityRVAdapter.MyItemClickListener {
            override fun onItemClick(community: Review) {
                if (isReviewTabSelected) {
                    // 리뷰 상세 페이지로 이동
                    val intent = Intent(activity, CommunityActivity::class.java)
                    intent.putExtra("REVIEW_ID", community.reviewId)
                    startActivity(intent)
                } else {
                    // 게시판 상세 페이지로 이동
                    val intent = Intent(activity, CommunityActivity::class.java)
                    intent.putExtra("POST_ID", community.reviewId)
                    startActivity(intent)
                }
            }
        })
    }

    // 선택된 탭 설정
    private fun setSelectedTab(selectedButton: ImageButton, selectedText: TextView) {
        communityTabSelection()

        selectedButton.isSelected = true
        selectedText.isSelected = true
    }

    // 모든 탭 초기화
    private fun communityTabSelection() {
        binding.communityReviewIb.isSelected = false
        binding.communityReviewTv.isSelected = false
        binding.communityQnaIb.isSelected = false
        binding.communityQnaTv.isSelected = false
    }
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
            override fun onItemClick(community: Community) {
                val intent = Intent(activity, CommunityActivity::class.java)
                startActivity(intent)
            }
        })

        binding.communityRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initRV() {
        var tradRVAdapter = SelecTraditionalRVAdapter()

        tradRVAdapter.setClickListener(object : SelecTraditionalRVAdapter.MyClickListener{
            override fun itemSelect(traditionalName : String) {
                binding.communityLocalTv.text = traditionalName
                binding.communityLocalDropIv.setImageResource(R.drawable.ic_drop)
                binding.communitySelectTraditionalRv.visibility = View.GONE
            }
        })

        binding.communitySelectTraditionalRv.adapter = tradRVAdapter
        binding.communitySelectTraditionalRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    private fun loadSampleData(){
        CommunityDatas.add(Community("이건 커뮤니티 가게",4.5f,"맛있당",16,16))
        CommunityDatas.add(Community("맛없는 가게",5f,"맛있당",1,2,R.drawable.img_food))
        CommunityDatas.add(Community("배고 가게",1f,"맛있당",6,2,R.drawable.img_food))
        CommunityDatas.add(Community("파여 가게",2.5f,"맛있당",1,9,R.drawable.img_food))
        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
    }
}