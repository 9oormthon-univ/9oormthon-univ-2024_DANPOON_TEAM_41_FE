package com.example.allgoing.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.allgoing.databinding.ActivityCommunityBinding
import com.example.allgoing.retrofit.DTO.Response.ReviewDetailRes
import com.example.allgoing.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommunityActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommunityBinding
    var reviewId: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 뒤로가기 버튼 클릭 시 종료
        binding.communityBackIv.setOnClickListener {
            finish()
        }

        // 리뷰 상세 데이터 가져오기
        getReviewDetail(reviewId)
    }

    private fun getReviewDetail(reviewId: Int) {
        RetrofitClient.service.getReviewDetail(MainActivity.accessToken, reviewId)
            .enqueue(object : Callback<ReviewDetailRes> {
                override fun onFailure(call: Call<ReviewDetailRes>, t: Throwable) {
                    Log.e("retrofit", t.toString())
                }

                override fun onResponse(
                    call: Call<ReviewDetailRes>,
                    response: Response<ReviewDetailRes>
                ) {
                    Log.d("retrofit", response.toString())
                    Log.d("retrofit", response.code().toString())
                    Log.d("retrofit", response.message().toString())

                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        if (responseBody != null) {
                            val review = responseBody.information

                            // 제목, 작성자, 날짜, 내용 표시
                            binding.communityTitleTv.text = review.reviewTitle
                            binding.communityNameTv.text = review.writerName
                            binding.communityDateTv.text = review.createdAt
                            binding.communityBodyTv.text = review.reviewContent

                            // 사진 표시 로직
                            val imageList = review.reviewImages
                            when (imageList.size) {
                                1 -> {
                                    binding.communityImg1Iv.visibility = View.VISIBLE
                                    binding.communityImg2Iv.visibility = View.GONE
                                    binding.communityImg3Iv.visibility = View.GONE
                                    loadImage(binding.communityImg1Iv, imageList[0].reviewImageUrl)
                                }
                                2 -> {
                                    binding.communityImg1Iv.visibility = View.VISIBLE
                                    binding.communityImg2Iv.visibility = View.VISIBLE
                                    binding.communityImg3Iv.visibility = View.GONE
                                    loadImage(binding.communityImg1Iv, imageList[0].reviewImageUrl)
                                    loadImage(binding.communityImg2Iv, imageList[1].reviewImageUrl)
                                }
                                3 -> {
                                    binding.communityImg1Iv.visibility = View.VISIBLE
                                    binding.communityImg2Iv.visibility = View.VISIBLE
                                    binding.communityImg3Iv.visibility = View.VISIBLE
                                    loadImage(binding.communityImg1Iv, imageList[0].reviewImageUrl)
                                    loadImage(binding.communityImg2Iv, imageList[1].reviewImageUrl)
                                    loadImage(binding.communityImg3Iv, imageList[2].reviewImageUrl)
                                }
                                else -> {
                                    binding.communityImg1Iv.visibility = View.GONE
                                    binding.communityImg2Iv.visibility = View.GONE
                                    binding.communityImg3Iv.visibility = View.GONE
                                }
                            }
                        }
                    } else {
                        Log.e("retrofit", "Response failed: ${response.errorBody()?.string()}")
                    }
                }
            })
    }

    private fun loadImage(imageView: ImageView, imageUrl: String) {
        Glide.with(this)
            .load(imageUrl)
            .into(imageView)
    }
}
