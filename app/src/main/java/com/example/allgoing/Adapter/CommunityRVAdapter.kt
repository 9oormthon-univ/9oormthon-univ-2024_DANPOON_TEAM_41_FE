package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemCommunityBinding
import com.example.allgoing.dataclass.Community

class CommunityRVAdapter() :RecyclerView.Adapter<CommunityRVAdapter.ViewHolder>() {
    var communitylist = ArrayList<Community>()

    fun interface MyItemClickListener{
        fun onItemClick(community: Community)
    }

    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itenClickListener: MyItemClickListener){
        myItemClickListener = itenClickListener
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
                fun bind(community: Community) {
                    binding.itemCommunityShopTv.text =community.community_shop
                    binding.itemCommunityBodyTv.text = community.community_body
                    binding.itemCommunityStarIv.rating = community.community_star.toFloat()
                    binding.itemCommunityLikeNumTv.text = community.community_likenum.toString()
                    binding.itemCommunityCommentNumTv.text = community.community_commentnum.toString()
                    if (community.community_Img != null) {
                        binding.itemCommunityImgIv.setImageResource(community.community_Img!!)
                        binding.itemCommunityImgIv.visibility = View.VISIBLE
                    } else {
                        binding.itemCommunityImgIv.visibility = View.GONE
                    }
                }
            }
}