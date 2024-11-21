package com.example.allgoing.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allgoing.Adapter.OrderCalendarRVAdapter
import com.example.allgoing.databinding.FragmentDetailHomeBinding
import com.example.allgoing.databinding.FragmentDetailOrderBinding

class DetailOrderFragment : Fragment(){
    lateinit var binding: FragmentDetailOrderBinding

    var date_list = arrayListOf(
        0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,
        0,0,0,0,0,0,0,
        0,0,0,0,0,0,0
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailOrderBinding.inflate(inflater,container,false)

        return binding.root
    }

    private fun initRV() {

        val calRVAdapter = OrderCalendarRVAdapter(date_list)

        calRVAdapter.setClickListener(object : OrderCalendarRVAdapter.MyClickListener{
            override fun itemSelect(posion:Int) {

                initRV()
            }
        })
        binding.orderCalendar.adapter = calRVAdapter
        binding.orderCalendar.layoutManager = GridLayoutManager(activity, 7)
    }

}