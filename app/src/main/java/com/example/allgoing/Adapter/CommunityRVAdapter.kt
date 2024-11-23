package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allgoing.databinding.ItemCommunityBinding
import com.example.allgoing.retrofit.DTO.DataClass.Review
import com.example.allgoing.retrofit.DTO.Response.ReviewTraditionalRes

class CommunityRVAdapter() :RecyclerView.Adapter<CommunityRVAdapter.ViewHolder>() {
    var communitylist = ArrayList<Review>()

    fun interface MyItemClickListener{
        fun onItemClick(community: Review)
    }

    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val binding = ItemCommunityBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return communitylist.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(communitylist[position])
        holder.itemView.setOnClickListener{
            myItemClickListener.onItemClick(communitylist[position])
        }
    }
    inner class ViewHolder(private val binding: ItemCommunityBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(community: Review) {
            binding.itemCommunityShopTv.text = community.reviewTitle
            binding.itemCommunityBodyTv.text = community.reviewContent
            binding.itemCommunityStarIv.rating = community.star.toFloat()
            binding.itemCommunityLikeNumTv.text = community.likeCount.toString()
            val picList = community.reviewImages
            if (picList.isNotEmpty()) {
                // Glide를 사용하여 이미지 로드
                binding.itemCommunityImgIv.visibility = View.VISIBLE
                Glide.with(binding.itemCommunityImgIv.context)
                    .load(picList[0].reviewImageUrl) // URL 로드
                    .into(binding.itemCommunityImgIv) // 이미지뷰에 로드
            } else {
                // 이미지가 없으면 숨김 처리
                binding.itemCommunityImgIv.visibility = View.GONE
            }
        }
    }
}