package com.example.allgoing.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.allgoing.CustomViewer
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentHomeBinding
import com.example.allgoing.retrofit.DTO.Response.CatAssListRes
import com.example.allgoing.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(){
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)

        binding.homeShopIc.setOnClickListener {
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm,ShopFragment()).commitAllowingStateLoss()
        }

        init3D()

        return binding.root
    }

    var customViewer: CustomViewer = CustomViewer()

    private fun init3D() {
        var surfaceView = binding.homeModel
//        surfaceView.isVisible = true
        var acc1 = MainActivity.acc1
        var acc2 = MainActivity.acc2

        customViewer.run {
            loadEntity()
            setSurfaceView(requireNotNull(surfaceView))

            //directory and model each as param
            loadGlb(requireContext() ,"cat_$acc1$acc2");

            loadIndirectLight(requireContext(), "venetian_crossroads_2k")
//            loadEnviroment(requireContext(), "venetian_crossroads_2k")
        }

    }

    override fun onResume() {
        super.onResume()
        customViewer.run {
            onResume()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        customViewer.run {
            onDestroy()
        }
    }

    override fun onPause() {
        super.onPause()
        customViewer.run {
            onPause()
        }
    }
}