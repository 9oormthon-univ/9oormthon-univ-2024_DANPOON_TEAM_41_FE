package com.example.allgoing.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.allgoing.R
import com.example.allgoing.databinding.ActivityMainBinding
import com.example.allgoing.fragment.CommunityFragment
import com.example.allgoing.fragment.HomeFragment
import com.example.allgoing.fragment.LoginFragment
import com.example.allgoing.fragment.MapFragment
import com.example.allgoing.fragment.MypageFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    companion object {
        var accessToken :String = ""
        var name = "로그인 후 이용하세요"
        var email = "test@test.test"
        var acc1 = ""
        var acc2 = ""

        var img = ""
    }

    var frag : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainBottomNav1.setOnClickListener {
            frag = 0
            initBottoNav()}
        binding.mainBottomNav2.setOnClickListener {
            frag = 1
            initBottoNav()
        }
        binding.mainBottomNav3.setOnClickListener {
            frag = 2
            initBottoNav()
        }
        binding.mainBottomNav4.setOnClickListener {
            frag = 3
            initBottoNav()
        }


        initBottoNav()

        if(accessToken == "") supportFragmentManager.beginTransaction().replace(R.id.main_frm, LoginFragment()).commitAllowingStateLoss()
    }

    private fun initBottoNav() {
        binding.mainHomeIv.setImageResource(R.drawable.ic_home_off)
        binding.mainHomeTv.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.mainMapIv.setImageResource(R.drawable.ic_map_off)
        binding.mainMapTv.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.mainCommunityIv.setImageResource(R.drawable.ic_community_off)
        binding.mainCommunityTv.setTextColor(ContextCompat.getColor(this, R.color.black))
        binding.mainMypageIv.setImageResource(R.drawable.ic_mypage_off)
        binding.mainMypageTv.setTextColor(ContextCompat.getColor(this, R.color.black))

        when(frag){
            0 -> {supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, HomeFragment())
                .commitAllowingStateLoss()
                binding.mainHomeIv.setImageResource(R.drawable.ic_home_on)
                binding.mainHomeTv.setTextColor(ContextCompat.getColor(this, R.color.primary_color))
            }

            1 -> {supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MapFragment())
                .commitAllowingStateLoss()
                binding.mainMapIv.setImageResource(R.drawable.ic_map_on)
                binding.mainMapTv.setTextColor(ContextCompat.getColor(this, R.color.primary_color))
            }

            2 -> {supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, CommunityFragment())
                .commitAllowingStateLoss()
                binding.mainCommunityIv.setImageResource(R.drawable.ic_community_on)
                binding.mainCommunityTv.setTextColor(ContextCompat.getColor(this, R.color.primary_color))
            }

            3 -> {supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, MypageFragment())
                .commitAllowingStateLoss()
                binding.mainMypageIv.setImageResource(R.drawable.ic_mypage_on)
                binding.mainMypageTv.setTextColor(ContextCompat.getColor(this, R.color.primary_color))
            }
        }

    }
}