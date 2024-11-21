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
import com.example.allgoing.Interface.DialogInterface
import com.example.allgoing.R
import com.example.allgoing.activity.CommunityActivity
import com.example.allgoing.activity.customDialog
import com.example.allgoing.databinding.FragmentWriteCommuBinding

class WriteCommuFragment : Fragment(), DialogInterface {

    private lateinit var binding: FragmentWriteCommuBinding
    private var imageUris = mutableListOf<Uri>() // 추가된 이미지 URI를 저장
    private val maxImages = 3 // 최대 이미지 개수

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWriteCommuBinding.inflate(inflater, container, false)

        // 뒤로 가기 버튼 클릭 이벤트
        binding.writeCommuBackIv.setOnClickListener {
            activity?.onBackPressed()
        }

        // 게시하기 버튼 클릭 시 다이얼로그 호출
        binding.writeCommuCheckIv.setOnClickListener {
            // 첫 번째 다이얼로그: "글을 게시하시겠어요?"
            val confirmDialog = customDialog(
                confirmDialogInterface = object : DialogInterface {
                    override fun onClickYesButton(id: Int) {
                        // 두 번째 다이얼로그 호출: "글이 게시되었어요."
                        val postedDialog = customDialog(
                            confirmDialogInterface = this@WriteCommuFragment,
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
        binding.writeCommuPlusImgIv.setOnClickListener {
            if (imageUris.size < maxImages) {
                openGallery()
            }
        }

        // EditText 테두리 변경
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
                binding.writeCommuPlusImg1Iv.visibility = View.VISIBLE
                binding.writeCommuPlusImg1Iv.setImageURI(uri)
            }
            2 -> {
                binding.writeCommuPlusImg2Iv.visibility = View.VISIBLE
                binding.writeCommuPlusImg2Iv.setImageURI(uri)
            }
            3 -> {
                binding.writeCommuPlusImg3Iv.visibility = View.VISIBLE
                binding.writeCommuPlusImg3Iv.setImageURI(uri)
            }
        }

        // 최대 개수에 도달하면 이미지 추가 버튼 숨기기
        if (imageUris.size == maxImages) {
            binding.writeCommuPlusImgIv.visibility = View.GONE
        }
    }

    // 테두리 강조를 위한 EditText 처리
    private fun setupEditTextFocusHandling() {
        // 제목 EditText 클릭 및 포커스 처리
        binding.writeCommuTitleEditView.setOnClickListener {
            setFocusOnTitle()
        }
        binding.writeCommuTitleEditEd.setOnClickListener {
            setFocusOnTitle()
        }
        binding.writeCommuTitleEditEd.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) setFocusOnTitle()
        }

        // 내용 EditText 클릭 및 포커스 처리
        binding.writeCommutBodyEt.setOnClickListener {
            setFocusOnContent()
        }
        binding.writeCommutBodyEt.setOnClickListener {
            setFocusOnContent()
        }
        binding.writeCommutBodyEt.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) setFocusOnContent()
        }
    }

    // 제목 EditText 테두리 강조
    private fun setFocusOnTitle() {
        binding.writeCommuTitleEditView.setBackgroundResource(R.drawable.bg_rectangle_white_stroke_primary_8)
        binding.writeCommutBodyEt.setBackgroundResource(R.drawable.bg_rectangle_white_radius8_gray45_1)
    }

    // 내용 EditText 테두리 강조
    private fun setFocusOnContent() {
        binding.writeCommutBodyEt.setBackgroundResource(R.drawable.bg_rectangle_white_stroke_primary_8)
        binding.writeCommuTitleEditView.setBackgroundResource(R.drawable.bg_rectangle_white_radius8_gray45_1)
    }

    // DialogInterface 구현
    override fun onClickYesButton(id: Int) {
        if (id == 2) {
            // "내가 쓴 글 보러가기" 버튼 클릭 시 CommunityActivity로 이동
            val intent = Intent(activity, CommunityActivity::class.java)
            startActivity(intent)
        }
    }
}
