package com.example.allgoing.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allgoing.Adapter.HistoryOrderRVAdapter
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentHistoryCommentBinding
import com.example.allgoing.databinding.FragmentHistoryCommuBinding
import com.example.allgoing.databinding.FragmentHistoryOrderBinding
import com.example.allgoing.databinding.FragmentMapBinding
import com.example.allgoing.dataclass.HistoryOrder
import com.example.allgoing.retrofit.DTO.Response.ReservationMyRes
import com.example.allgoing.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HistoryOrderFragment : Fragment(){

    lateinit var binding: FragmentHistoryOrderBinding
    private lateinit var adapter: HistoryOrderRVAdapter

    private val historyorderList = ArrayList<HistoryOrder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryOrderBinding.inflate(inflater,container,false)

        initPostRecyclerView()
//        loadSampleData()
        getReservationMy()

        binding.historyOrderBackIv.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageFragment()).commitAllowingStateLoss()
        }
        return binding.root
    }

    private fun initPostRecyclerView() {
        adapter = HistoryOrderRVAdapter(historyorderList)
        binding.historyOrderRv.adapter = adapter
        binding.historyOrderRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun getReservationMy() {
        RetrofitClient.service.getReservationMy(MainActivity.accessToken).enqueue(object : Callback<ReservationMyRes> {
            override fun onFailure(call: Call<ReservationMyRes>, t: Throwable) {
                Log.e("retrofit", "오류 발생: ${t.message}")
            }

            override fun onResponse(call: Call<ReservationMyRes>, response: Response<ReservationMyRes>) {
                Log.d("retrofit", "응답 코드: ${response.code()}")
                Log.d("retrofit", "응답 메시지: ${response.message()}")

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.check) {
                        // 기존 데이터 초기화
                        historyorderList.clear()

                        // JSON 데이터 파싱 및 리스트 추가
                        responseBody.information.forEach { info ->
                            val productsSummary = info.products.joinToString(", ") {
                                "${it.productName} x${it.quantity}"
                            }

                            historyorderList.add(
                                HistoryOrder(
                                    historyorder_shop = info.storeName,
                                    historyorder_date = "${info.reservationDate} ${info.reservationTime}",
                                    historyorder_body = productsSummary
                                )
                            )
                        }

                        // RecyclerView 업데이트
                        adapter.notifyDataSetChanged()
                    } else {
                        Log.e("retrofit", "응답이 성공적이나 데이터가 비어있거나 'check' 값이 false입니다.")
                    }
                } else {
                    Log.e("retrofit", "응답 실패: ${response.errorBody()?.string()}")
                }
            }
        })
    }






//    private fun loadSampleData() {
//        // 샘플 데이터 로드 (임시 데이터)
//        historyorderList.add(HistoryOrder("고양이 가게", "2024.10.11", "참치, 츄르"))
//        historyorderList.add(HistoryOrder("고양이네 생선가게", "2024.10.13", "갈치,고등어 외2. . ."))
//        historyorderList.add(HistoryOrder("강아지 가게", "2024.10.11", "참치, 츄르"))
//        historyorderList.add(HistoryOrder("고양이 가게", "2024.10.11", "참치, 츄르"))
//        historyorderList.add(HistoryOrder("고양이 가게", "2024.10.11", "참치, 츄르"))
//        historyorderList.add(HistoryOrder("고양이 가게", "2024.10.11", "참치, 츄르"))
//    }

}