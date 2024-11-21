package com.example.allgoing.activity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.allgoing.Interface.DialogInterface
import com.example.allgoing.databinding.DialogWriteConfirmBinding

class customDialog(
    private val confirmDialogInterface: DialogInterface,
    private val title: String,
    private val buttonTextYes: String,
    private val buttonTextNo: String?,
    private val id: Int
) : DialogFragment() {

    private var _binding: DialogWriteConfirmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DialogWriteConfirmBinding.inflate(inflater, container, false)

        // 다이얼로그 배경을 투명하게 설정
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 제목 설정
        binding.dialogTotalTitleTv.text = title

        // 확인 버튼 설정
        binding.dialogTotalChoose1Tv.text = buttonTextYes
        binding.dialogTotalChoose1Tv.setOnClickListener {
            confirmDialogInterface.onClickYesButton(id)
            dismiss()
        }

        // 취소 버튼 설정
        if (buttonTextNo.isNullOrEmpty()) {
            binding.dialogTotalChoose2Tv.visibility = View.GONE
        } else {
            binding.dialogTotalChoose2Tv.text = buttonTextNo
            binding.dialogTotalChoose2Tv.setOnClickListener {
                dismiss()
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            // 배경을 반투명하게 설정
            setDimAmount(0.5f)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // 메모리 누수 방지
    }
}
