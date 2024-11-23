package com.example.allgoing.fragment

import android.app.appsearch.Migrator
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.allgoing.Adapter.DetailNoticeRVAdapter
import com.example.allgoing.Adapter.ShopRVAdapter
import com.example.allgoing.CustomViewer
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentShopBinding
import com.example.allgoing.dataclass.Shop
import com.example.allgoing.retrofit.RetrofitClient
import com.example.allgoing.retrofit.DTO.Response.CatAssListRes
import com.example.allgoing.retrofit.DTO.Response.CatExpRes
import com.example.allgoing.retrofit.DTO.Response.ShopItemListRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ShopFragment : Fragment() {
    private lateinit var binding: FragmentShopBinding
    private lateinit var adapter: ShopRVAdapter
    private val shopList = ArrayList<Shop>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopBinding.inflate(inflater, container, false)

        initMenuRecyclerView()
        //loadInitialData()
        load1Data()

        setSelectedTab(binding.shopAssIb, binding.shopAssTv)

        // 탭 버튼 클릭 리스너
        binding.shopAssIb.setOnClickListener {
            setSelectedTab(binding.shopAssIb, binding.shopAssTv)
            load1Data()
            initMenuRecyclerView()
            //fetchShopItemList("accessories") // 악세서리 데이터 조회
        }
        binding.shopShoesIb.setOnClickListener {
            setSelectedTab(binding.shopShoesIb, binding.shopShoesTv)
            load2Data()
            initMenuRecyclerView()
            //fetchShopItemList("shoes") // 신발 데이터 조회
        }

        // 뒤로 가기 버튼
        binding.shopBackIv.setOnClickListener {
//            Toast.makeText(context, "뒤로 가기 버튼 클릭", Toast.LENGTH_SHORT).show()
//            // 액티비티나 다른 프래그먼트로 이동 구현
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,HomeFragment()).commitAllowingStateLoss()

        }

        init3D()

        return binding.root
    }

    var customViewer: CustomViewer = CustomViewer()

    private fun init3D() {
        var surfaceView = binding.shopModel
        var model = "cat"
//        surfaceView.isVisible = true
        var acc1 = MainActivity.acc1
        var acc2 = MainActivity.acc2

        customViewer.run {
            loadEntity()
            setSurfaceView(requireNotNull(surfaceView))

            //directory and model each as param
            loadGlb(requireContext() ,"cat_$acc1$acc2");

            loadIndirectLight(requireContext(), "venetian_crossroads_2k")
//            loadEnviroment(requireContext(), "venetian_crossroads_2k")
        }

    }

    override fun onResume() {
        super.onResume()
        customViewer.run {
            onResume()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        customViewer.run {
            onDestroy()
        }
    }

    override fun onPause() {
        super.onPause()
        customViewer.run {
            onPause()
        }
    }

     private fun setSelectedTab(selectedButton: ImageButton, selectedText: TextView) {
        shopTabSelection()
        selectedButton.isSelected = true
        selectedText.isSelected = true
    }

    private fun shopTabSelection() {
        binding.shopAssIb.isSelected = false
        binding.shopAssTv.isSelected = false
        binding.shopShoesIb.isSelected = false
        binding.shopShoesTv.isSelected = false
    }

    private fun initMenuRecyclerView() {
        adapter = ShopRVAdapter(shopList)

        adapter.setClickListener(object : ShopRVAdapter.MyClickListener{
            override fun itemSelect(id: String) {
                if (id<"7") {
                    MainActivity.acc1 = id
                } else {
                    MainActivity.acc2 = id
                }

                (activity as MainActivity).supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frm,ShopFragment()).commitAllowingStateLoss()

            }
        })

        binding.shopItemRv.adapter = adapter
        binding.shopItemRv.layoutManager = GridLayoutManager(context, 4)
    }

//    private fun loadInitialData() {
//        fetchCatAssList() // 코인 및 착용 아이템 조회
//        fetchShopItemList("accessories") // 기본적으로 악세서리 데이터 조회
//    }
//
//
//    private fun fetchCatAssList() {
//        RetrofitClient.service.getCatAssList("Bearer [YourTokenHere]").enqueue(object : Callback<CatAssListRes> {
//            override fun onFailure(call: Call<CatAssListRes>, t: Throwable) {
//                Log.e("retrofit", "Failed to fetch CatAssList: $t")
//            }
//
//            override fun onResponse(call: Call<CatAssListRes>, response: Response<CatAssListRes>) {
//                if (response.isSuccessful) {
//                    val data = response.body()?.information
//                    if (data != null) {
//                        binding.shopMoneyTv.text = "Coins: ${data.coin}"
//                        shopList.clear()
//                        for (item in data.catItems) {
//                            shopList.add(Shop(null, item.itemName))
//                        }
//                        adapter.notifyDataSetChanged()
//                    } else {
//                        Log.e("retrofit", "CatAssList response body is null")
//                    }
//                } else {
//                    Log.e("retrofit", "CatAssList response error: ${response.errorBody()?.string()}")
//                }
//            }
//        })
//    }
//
//    private fun fetchShopItemList(category: String) {
//        RetrofitClient.service.getShopItemList("Bearer [YourTokenHere]").enqueue(object : Callback<ShopItemListRes> {
//            override fun onFailure(call: Call<ShopItemListRes>, t: Throwable) {
//                Log.e("retrofit", "Failed to fetch ShopItemList: $t")
//            }
//
//            override fun onResponse(call: Call<ShopItemListRes>, response: Response<ShopItemListRes>) {
//                if (response.isSuccessful) {
//                    val data = response.body()
//                    if (data != null) {
//                        val items = if (category == "accessories") data.accessories else data.shoes
//                        shopList.clear()
//                        for (item in items) {
//                            shopList.add(
//                                Shop(
//                                    shop_item_Img = R.drawable.ic_shop_non, // 서버에서 이미지 URL 로드 시 Glide 사용
//                                    shop_item_name = item.
//                                )
//                            )
//                        }
//                        adapter.notifyDataSetChanged()
//                    } else {
//                        Log.e("retrofit", "ShopItemList response body is null")
//                    }
//                } else {
//                    Log.e("retrofit", "ShopItemList response error: ${response.errorBody()?.string()}")
//                }
//            }
//        })
//    }

    private fun load1Data(){
        shopList.clear()
        shopList.add(Shop(R.drawable.ic_shop_non,"벗어두기",""))
        shopList.add(Shop(R.drawable.a1,"빨간 리본","1"))
        shopList.add(Shop(R.drawable.a2,"대왕 리본\n넥타이","2"))
        shopList.add(Shop(R.drawable.a3,"꽥꽥!\n오리","3"))
        shopList.add(Shop(R.drawable.a4,"초록\n산타모자","4"))
        shopList.add(Shop(R.drawable.a6,"빨강\n산타모자","6"))
    }


    private fun load2Data(){
        shopList.clear()
        shopList.add(Shop(R.drawable.ic_shop_non,"벗어두기",""))
        shopList.add(Shop(R.drawable.a7,"고양이 장화","7"))
        shopList.add(Shop(R.drawable.a8,"빨강\n산타부츠","8"))
        shopList.add(Shop(R.drawable.a9,"초록\n산타부츠","9"))
    }
}
