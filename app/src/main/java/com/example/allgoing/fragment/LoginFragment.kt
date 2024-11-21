package com.example.allgoing.fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    // 카카오 로그인 콜백
    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.d(TAG, "로그인 실패: ${error.localizedMessage}")
        } else if (token != null) {
            Log.d(TAG, "로그인 성공! ${token.accessToken}")
            goToMain()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 로그인을 초기화
        initView()

        binding.loginBackIv.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,MypageFragment()).commitAllowingStateLoss()
        }

        // 디버그용 키 해시 로그
        val keyHash = Utility.getKeyHash(requireContext())
        Log.d("KeyHash", keyHash)
    }

    private fun initView(){
        val context: Context = requireContext()

        // 카카오 로그인 버튼 클릭 이벤트
        binding.loginBtnFl.setOnClickListener {
            Log.d(TAG, "로그인 버튼 클릭됨")

            if (UserApiClient.instance.isKakaoTalkLoginAvailable(context)){
                //카카오톡으로 로그인
                UserApiClient.instance.loginWithKakaoTalk(context) {token, error ->
                    if (error != null) {
                        Log.d(TAG, ",로그인 실패: ${error.localizedMessage}")

                        //사용자가 로그인 취소한 경우 처리
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }
                        //카카오톡 실패 시 카카오 계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
                    } else if (token != null) {
                        Log.d(TAG, "로그인 성공! ${token.accessToken}")
                        goToMain()
                    }
                }
            } else {
                //카카오톡 계정으로 로그인
                UserApiClient.instance.loginWithKakaoAccount(context, callback = callback)
            }
        }
    }

    private fun goToMain(){
        (activity as MainActivity).supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm,MypageFragment()).commitAllowingStateLoss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
