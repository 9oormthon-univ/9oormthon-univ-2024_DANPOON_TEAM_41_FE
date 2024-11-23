package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.Adapter.CommunityRVAdapter.MyItemClickListener
import com.example.allgoing.databinding.ItemCommunityBinding
import com.example.allgoing.databinding.ItemDetailReviewBinding
import com.example.allgoing.dataclass.DetailReview

class DetailReviewRVAdapter : RecyclerView.Adapter<DetailReviewRVAdapter.ViewHolder>() {
    var detailreviewlist = ArrayList<DetailReview>()

    fun interface MyItemClickListener{
        fun onItemClick(detailReview: DetailReview)
    }

    private lateinit var myItemClickListener: MyItemClickListener
    fun setMyItemClickListener(itemClickListener: MyItemClickListener){
        myItemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val binding = ItemDetailReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(detailreviewlist[position])
        holder.itemView.setOnClickListener{
            myItemClickListener.onItemClick(detailreviewlist[position])
        }
    }

    override fun getItemCount(): Int {
        return detailreviewlist.size
    }

    inner class ViewHolder(private val binding: ItemDetailReviewBinding) :
    RecyclerView.ViewHolder(binding.root) {
        fun bind(detailReview: DetailReview){
            binding.itemDetailReviewNameTv.text =detailReview.detail_review_name
            binding.itemDetailReviewStarIv.rating = detailReview.detail_review_star.toFloat()
            binding.itemDetailReviewScoreTv.text = detailReview.detail_review_score.toString()
            binding.itemDetailReviewDateTv.text = detailReview.detail_review_date
            binding.itemDetailReviewBodyTv.text = detailReview.detail_review_body
            binding.itemDetailReviewLikeNumTv.text = detailReview.detail_review_likenum.toString()
            binding.itemDetailReviewCommentNumTv.text = detailReview.detail_review_commentnum.toString()
        }

    }

}