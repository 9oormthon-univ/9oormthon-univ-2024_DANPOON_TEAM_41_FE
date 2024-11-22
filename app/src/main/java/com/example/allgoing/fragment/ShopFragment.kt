package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.allgoing.Adapter.DetailHomeMenuRVAdapter
import com.example.allgoing.Adapter.ShopRVAdapter
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentShopBinding
import com.example.allgoing.dataclass.Shop

class ShopFragment : Fragment(){
    lateinit var binding: FragmentShopBinding

    private lateinit var adapter: ShopRVAdapter
    private val shopList = ArrayList<Shop>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShopBinding.inflate(inflater,container,false)

        initMenuRecyclerView()
        loadSampleData()

        //탭 색 바꾸자잉
        setSelectedTab(binding.shopAssIb, binding.shopAssTv)

        binding.shopAssIb.setOnClickListener{
            setSelectedTab(binding.shopAssIb, binding.shopAssTv)
        }
        binding.shopShoesIb.setOnClickListener {
            setSelectedTab(binding.shopShoesIb, binding.shopShoesTv)
        }

        binding.shopBackIv.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,HomeFragment()).commitAllowingStateLoss()
        }

        return binding.root
    }

    private fun setSelectedTab(selectedButton: ImageButton, selectedText: TextView){
        shopTabSelection()

        selectedButton.isSelected = true
        selectedText.isSelected = true
    }

    private fun shopTabSelection(){
        binding.shopAssIb.isSelected = false
        binding.shopAssTv.isSelected = false
        binding.shopShoesIb.isSelected = false
        binding.shopShoesTv.isSelected = false
    }
    private fun initMenuRecyclerView() {
        adapter = ShopRVAdapter(shopList)
        binding.shopItemRv.adapter = adapter
        binding.shopItemRv.layoutManager = GridLayoutManager(context,4)
    }

    private fun loadSampleData(){
        shopList.add(Shop(R.drawable.ic_shop_non,"접어두기"))
        shopList.add(Shop(R.drawable.img_food,"원 테스트"))
        shopList.add(Shop(R.drawable.ic_shop_non,"초록색 산타모자"))
        shopList.add(Shop(R.drawable.img_food,"초록\n산타모자"))
        shopList.add(Shop(R.drawable.img_food,"둘은 글 테스트"))
    }
}