package com.example.allgoing.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.allgoing.databinding.ItemDetailNoticeBinding

class DetailNoticeRVAdapter(): RecyclerView.Adapter<DetailNoticeRVAdapter.ViewHolder>() {

    var date_list = arrayListOf<String>("2024.10.23","2024.10.23","2024.10.23","2024.10.23")
    var notice_list = arrayListOf<String>("오늘 신메뉴 들어왔습니다!","오늘 신메뉴 들어왔습니다!","오늘 신메뉴 들어왔습니다!")


    fun interface MyClickListener {
        fun itemSelect(posion:Int)
    }

    lateinit var myClickListener : MyClickListener

    fun setClickListener(clickListener: MyClickListener){
        myClickListener = clickListener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDetailNoticeBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
        holder.itemView.setOnClickListener {
            myClickListener.itemSelect(position)
        }
    }

    override fun getItemCount(): Int = notice_list.size

    inner class ViewHolder(val binding: ItemDetailNoticeBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.itemDetailNoticeDate.text = date_list[position]
            binding.itemDetailNoticeContent.text = notice_list[position]
        }
    }

}