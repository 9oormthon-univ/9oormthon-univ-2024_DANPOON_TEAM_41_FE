package com.example.allgoing.fragment

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.allgoing.Adapter.DetailNoticeRVAdapter
import com.example.allgoing.Adapter.SelectShopRVAdapter
import com.example.allgoing.Interface.DialogInterface
import com.example.allgoing.R
import com.example.allgoing.activity.CommunityActivity
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.activity.customDialog
import com.example.allgoing.databinding.FragmentWriteReviewBinding

class WriteReviewFragment : Fragment(), DialogInterface {
    private lateinit var binding: FragmentWriteReviewBinding
    private var imageUris = mutableListOf<Uri>() // 추가된 이미지 URI를 저장
    private val maxImages = 3 // 최대 이미지 개수

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWriteReviewBinding.inflate(inflater, container, false)

        // 뒤로 가기 버튼 클릭 이벤트
        binding.writeReviewBackIv.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, CommunityFragment()).commitAllowingStateLoss()
        }

        binding.writeReviewSelectShopLayout.setOnClickListener {
            binding.writeReviewSelectShopRv.visibility = View.VISIBLE
            binding.writeReviewDropIv.setImageResource(R.drawable.ic_toggle_up)
            initRV()
        }

        // 게시하기 버튼 클릭 시 다이얼로그 호출
        binding.writeReviewCheckIv.setOnClickListener {
            val confirmDialog = customDialog(
                confirmDialogInterface = object : DialogInterface {
                    override fun onClickYesButton(id: Int) {
                        val postedDialog = customDialog(
                            confirmDialogInterface = this@WriteReviewFragment,
                            title = "글이 게시되었어요.",
                            buttonTextYes = "내가 쓴 글 보러가기",
                            buttonTextNo = "닫기",
                            id = 2
                        )
                        postedDialog.show(parentFragmentManager, "PostedDialog")
                    }
                },
                title = "글을 게시하시겠어요?",
                buttonTextYes = "게시하기",
                buttonTextNo = "취소",
                id = 1
            )
            confirmDialog.show(parentFragmentManager, "ConfirmDialog")
        }

        // 이미지 추가 버튼 클릭 이벤트
        binding.writeReviewPlusImgIv.setOnClickListener {
            if (imageUris.size < maxImages) {
                openGallery()
            }
        }

        // EditText 테두리 강조 기능 추가
        setupEditTextFocusHandling()

        return binding.root
    }

    // 갤러리 열기
    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        pickImageLauncher.launch(intent)
    }

    private val pickImageLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data
            if (imageUri != null && imageUris.size < maxImages) {
                addImageToView(imageUri)
            }
        }
    }

    // 선택한 이미지를 View에 추가
    private fun addImageToView(uri: Uri) {
        imageUris.add(uri) // 이미지 URI 저장

        when (imageUris.size) {
            1 -> {
                binding.writeReviewPlusImg1Iv.visibility = View.VISIBLE
                binding.writeReviewPlusImg1Iv.setImageURI(uri)
            }
            2 -> {
                binding.writeReviewPlusImg2Iv.visibility = View.VISIBLE
                binding.writeReviewPlusImg2Iv.setImageURI(uri)
            }
            3 -> {
                binding.writeReviewPlusImg3Iv.visibility = View.VISIBLE
                binding.writeReviewPlusImg3Iv.setImageURI(uri)
            }
        }

        // 최대 개수에 도달하면 이미지 추가 버튼 숨기기
        if (imageUris.size == maxImages) {
            binding.writeReviewPlusImgIv.visibility = View.GONE
        }
    }

    // 테두리 강조를 위한 EditText 처리
    private fun setupEditTextFocusHandling() {
        // 글 입력 EditText 클릭 및 포커스 처리
        binding.writeReviewBodyEt.setOnClickListener {
            setFocusOnEditText()
        }
        binding.writeReviewBodyEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) setFocusOnEditText()
        }
    }

    // 글 입력 EditText 테두리 강조
    private fun setFocusOnEditText() {
        binding.writeReviewBodyEt.setBackgroundResource(R.drawable.bg_rectangle_white_stroke_primary_8)
    }

    // DialogInterface 구현
    override fun onClickYesButton(id: Int) {
        if (id == 2) {
            val intent = Intent(activity, CommunityActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRV() {
        var shopRVAdapter = SelectShopRVAdapter()

        shopRVAdapter.setClickListener(object : SelectShopRVAdapter.MyClickListener{
            override fun itemSelect(shopName : String) {
                binding.writeReviewSelectShopTv.text = shopName
                binding.writeReviewDropIv.setImageResource(R.drawable.ic_drop)
                binding.writeReviewSelectShopRv.visibility = View.GONE
            }
        })

        binding.writeReviewSelectShopRv.adapter = shopRVAdapter
        binding.writeReviewSelectShopRv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    }
}
