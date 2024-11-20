package com.example.allgoing.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.allgoing.R
import com.example.allgoing.databinding.ActivityCommunityBinding
import com.example.allgoing.databinding.ActivityMainBinding
import com.example.allgoing.fragment.CommunityFragment
import com.example.allgoing.fragment.HomeFragment
import com.example.allgoing.fragment.MapFragment
import com.example.allgoing.fragment.MypageFragment

class CommunityActivity : AppCompatActivity() {
    lateinit var binding: ActivityCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.communityBackIv.setOnClickListener{
            finish()
        }

    }

}