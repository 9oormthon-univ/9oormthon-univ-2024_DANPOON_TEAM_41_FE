package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {
    private lateinit var binding: FragmentEditProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)

        // 뒤로 가기 버튼 클릭 이벤트
        binding.editProfileBackIv.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MypageFragment()).commitAllowingStateLoss()
        }

        // 닉네임 입력 뷰와 EditText 클릭 이벤트 처리
        binding.editProfileNicknameEditView.setOnClickListener {
            setFocusOnNickname()
        }
        binding.editProfileNicknameEditEd.setOnClickListener {
            setFocusOnNickname()
        }
        binding.editProfileNicknameEditEd.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) setFocusOnNickname()
        }

        // 이메일 입력 뷰와 EditText 클릭 이벤트 처리
        binding.editProfileEmailEditView.setOnClickListener {
            setFocusOnEmail()
        }
        binding.editProfileEmailEditEd.setOnClickListener {
            setFocusOnEmail()
        }
        binding.editProfileEmailEditEd.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) setFocusOnEmail()
        }

        return binding.root
    }

    // 닉네임 입력 뷰에 초점
    private fun setFocusOnNickname() {
        binding.editProfileNicknameEditView.setBackgroundResource(R.drawable.bg_rectangle_white_stroke_primary_8)
        binding.editProfileEmailEditView.setBackgroundResource(R.drawable.bg_rectangle_white_radius8_gray45_1)
    }

    // 이메일 입력 뷰에 초점
    private fun setFocusOnEmail() {
        binding.editProfileEmailEditView.setBackgroundResource(R.drawable.bg_rectangle_white_stroke_primary_8)
        binding.editProfileNicknameEditView.setBackgroundResource(R.drawable.bg_rectangle_white_radius8_gray45_1)
    }
}
