package com.example.allgoing.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.allgoing.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    private fun initBottoNav() {

    }
}