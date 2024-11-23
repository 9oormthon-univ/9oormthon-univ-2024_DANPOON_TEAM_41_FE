package com.example.allgoing.fragment

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentLoginBinding
import com.example.allgoing.retrofit.DTO.Request.LoginReq
import com.example.allgoing.retrofit.DTO.Response.LoginRes
import com.example.allgoing.retrofit.DTO.Response.kakaoUserRes
import com.example.allgoing.retrofit.KakaoClient
import com.example.allgoing.retrofit.RetrofitClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    lateinit var binding : FragmentLoginBinding

    private var accessToken = ""

    // 카카오 로그인 콜백
    private val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.d(TAG, "로그인 실패: ${error.localizedMessage}")
        } else if (token != null) {
            Log.d(TAG, "로그인 성공! ${token.accessToken}")
            accessToken = token.accessToken
            Log.d("accessToken",accessToken)
            kakaoLogin()
        }
    }

    private fun kakaoLogin(){
        UserApiClient.instance.me { user, error ->
            if (error != null) {
                Log.e(TAG, "사용자 정보 요청 실패", error)
            }
            else if (user != null) {
                Log.i(TAG, "사용자 정보 요청 성공" +
                        "\n회원번호: ${user.id}" +
                        "\n이메일: ${user.kakaoAccount?.email}1" +
                        "\n닉네임: ${user.kakaoAccount?.profile?.nickname}" +
                        "\n프로필사진: ${user.kakaoAccount?.profile?.thumbnailImageUrl}")

                MainActivity.name = user.kakaoAccount?.profile?.nickname.toString()
                MainActivity.email = user.kakaoAccount?.email.toString()

                login(user.kakaoAccount?.email.toString())
            }
        }
    }

    private fun login(email:String){
        RetrofitClient.service.postLogin(LoginReq(accessToken.toString(),email)).enqueue(object : Callback<LoginRes>{
            override fun onResponse(call: Call<LoginRes>, response: Response<LoginRes>) {
                Log.d("LoginFragment", response.body().toString())

                if(response.body()?.information != null){
                    MainActivity.accessToken = "Bearer "+response.body()!!.information.accessToken
                    goToMain()
                }
            }

            override fun onFailure(call: Call<LoginRes>, t: Throwable) {
                Log.e("LoginFragment",t.toString())
            }

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)

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
                        accessToken = token.accessToken
                        Log.d("accessToken",accessToken)
                        kakaoLogin()
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
}
