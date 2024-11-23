package com.example.allgoing.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allgoing.Adapter.CommunityRVAdapter
import com.example.allgoing.Adapter.SelecTraditionalRVAdapter
import com.example.allgoing.Adapter.SelectShopRVAdapter
import com.example.allgoing.R
import com.example.allgoing.activity.CommunityActivity
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentCommunityBinding
import com.example.allgoing.databinding.ItemSelectTraditionalBinding
import com.example.allgoing.dataclass.Community
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CommunityFragment : Fragment(){
    lateinit var binding: FragmentCommunityBinding
    lateinit var addbutton: ExtendedFloatingActionButton
    lateinit var writebutton: ExtendedFloatingActionButton
    lateinit var reviewbutton: ExtendedFloatingActionButton
    var isAllbuttonVisble = false

    private lateinit var adapter : CommunityRVAdapter
    var CommunityDatas = ArrayList<Community>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCommunityBinding.inflate(inflater,container,false)

        loadSampleData()
        initcommunityRecyclerView()

        //탭 색 바꾸기
        setSelectedTab(binding.communityReviewIb, binding.communityReviewTv)

        binding.communityReviewIb.setOnClickListener{
            setSelectedTab(binding.communityReviewIb, binding.communityReviewTv)
        }
        binding.communityQnaIb.setOnClickListener{
            setSelectedTab(binding.communityQnaIb,binding.communityQnaTv)
        }

        //플로팅 버튼 초기화
        addbutton = binding.communityFloatPlusBtn
        writebutton = binding.communityFloatCommuWriteBtn
        reviewbutton = binding.communityFloatReviewWriteBtn

        writebutton.visibility = View.GONE
        reviewbutton.visibility = View.GONE

        addbutton.setOnClickListener{
            toggleFloatingButtons()
        }

        writebutton.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,WriteCommuFragment()).commitAllowingStateLoss()
        }
        reviewbutton.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,WriteReviewFragment()).commitAllowingStateLoss()
        }

        binding.communitySelectShopLayout.setOnClickListener{
            binding.communitySelectTraditionalRv.visibility = View.VISIBLE
            binding.communityLocalDropIv.setImageResource(R.drawable.ic_toggle_up)
            initRV()
        }


        return binding.root
    }

    private fun setSelectedTab(selectedButton: ImageButton, selectedText: TextView){
        communityTabSelection()

        selectedButton.isSelected = true
        selectedText.isSelected = true
    }

    private fun communityTabSelection(){
        binding.communityReviewIb.isSelected = false
        binding.communityReviewTv.isSelected = false
        binding.communityQnaIb.isSelected = false
        binding.communityQnaTv.isSelected = false
    }

    private fun toggleFloatingButtons(){
        if(!isAllbuttonVisble){
            writebutton.visibility = View.VISIBLE
            reviewbutton.visibility = View.VISIBLE
            rotateAddButton(45f)
        }else {
            writebutton.visibility = View.GONE
            reviewbutton.visibility = View.GONE
            rotateAddButton(0f)
        }
        isAllbuttonVisble = !isAllbuttonVisble
    }

    private fun rotateAddButton(targetRotation: Float) {
        addbutton.animate()
            .rotation(targetRotation) // 원하는 각도로 회전
            .setDuration(300) // 애니메이션 지속 시간
            .setInterpolator(LinearInterpolator()) // 선형 속도
            .start()
    }

    fun initcommunityRecyclerView(){
        adapter = CommunityRVAdapter()
        adapter.communitylist = CommunityDatas
        adapter.setMyItemClickListener(object : CommunityRVAdapter.MyItemClickListener{
            override fun onItemClick(community: Community) {
                val intent = Intent(activity, CommunityActivity::class.java)
                startActivity(intent)
            }
        })

        binding.communityRv.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun initRV() {
        var tradRVAdapter = SelecTraditionalRVAdapter()

        tradRVAdapter.setClickListener(object : SelecTraditionalRVAdapter.MyClickListener{
            override fun itemSelect(traditionalName : String) {
                binding.communityLocalTv.text = traditionalName
                binding.communityLocalDropIv.setImageResource(R.drawable.ic_drop)
                binding.communitySelectTraditionalRv.visibility = View.GONE
            }
        })

        binding.communitySelectTraditionalRv.adapter = tradRVAdapter
        binding.communitySelectTraditionalRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }

    private fun loadSampleData(){
        CommunityDatas.add(Community("이건 커뮤니티 가게",4.5f,"맛있당",16,16))
        CommunityDatas.add(Community("맛없는 가게",5f,"맛있당",1,2,R.drawable.img_food))
        CommunityDatas.add(Community("배고 가게",1f,"맛있당",6,2,R.drawable.img_food))
        CommunityDatas.add(Community("파여 가게",2.5f,"맛있당",1,9,R.drawable.img_food))
        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
        CommunityDatas.add(Community("맛있는 가게",4.9f,"맛있당",1,2,R.drawable.img_food))
    }
}