package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.Adapter.CommunityRVAdapter.MyItemClickListener
import com.example.allgoing.databinding.ItemCommuBinding
import com.example.allgoing.databinding.ItemCommunityBinding
import com.example.allgoing.dataclass.Community
import com.example.allgoing.dataclass.HistoryCommu

class HistoryCommuRVAdapter() :RecyclerView.Adapter<HistoryCommuRVAdapter.ViewHolder>() {
    var historycommulist = ArrayList<HistoryCommu>()

    fun interface MyItemClickListener{
        fun onItemClick(historyCommu: HistoryCommu)
    }

    private lateinit var myItemClickListener: com.example.allgoing.Adapter.CommunityRVAdapter.MyItemClickListener
    fun setMyItemClickListener(itenClickListener: com.example.allgoing.Adapter.CommunityRVAdapter.MyItemClickListener){
        myItemClickListener = itenClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val binding = ItemCommuBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return historycommulist.size
    }

    override fun onBindViewHolder(holder: HistoryCommuRVAdapter.ViewHolder, position: Int) {
            holder.bind(historycommulist[position])
        holder.itemView.setOnClickListener{

        }
    }

    inner class ViewHolder(private val binding: ItemCommuBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(historyCommu: HistoryCommu){
                binding.itemCommuTitleTv.text =historyCommu.historycommu_shop
                binding.itemCommuBodyTv.text = historyCommu.historycommu_body
                binding.itemCommuLikeNumTv.text = historyCommu.historycommu_likenum.toString()
                binding.itemCommuCommentNumTv.text = historyCommu.historycommu_commentnum.toString()
                if (historyCommu.historycommu_Img != null) {
                    binding.itemCommuImgIv.setImageResource(historyCommu.historycommu_Img!!)
                    binding.itemCommuImgIv.visibility = View.VISIBLE
                } else {
                    binding.itemCommuImgIv.visibility = View.GONE
                }
            }

            }
}