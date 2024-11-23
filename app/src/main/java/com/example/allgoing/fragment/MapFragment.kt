package com.example.allgoing.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentMapBinding
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import java.lang.Exception

class MapFragment : Fragment(){
    lateinit var binding: FragmentMapBinding
    private var mapView: MapView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater,container,false)

        binding.mapBottom.setOnClickListener{
            (activity as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, DetailFragment())
                .commitAllowingStateLoss()
        }

        initMap()

        return binding.root
    }

    private fun initMap() {
        KakaoMapSdk.init(requireContext(), "6464ccc167d7d34887af472c191a971f")
        mapView = binding.kakaoMapView
//        mapView?.let {
//            Log.d("MapFragment", "MapView initialized successfully")
//        } ?: run {
//            Log.e("MapFragment", "Failed to initialize MapView")
//        }

        if (mapView != null) {

            mapView!!.start(object : MapLifeCycleCallback() {

                override fun onMapDestroy() {
                    Log.e("MapFragment", "Destroy to initialize MapView")
                }

                override fun onMapError(p0: Exception?) {
                    Log.e("MapFragment", "Failed to initialize MapView")
                }

            }, object : KakaoMapReadyCallback() {
                override fun onMapReady(p0: KakaoMap) {
                    Log.d("MapFragment", "MapView initialized successfully")
                }

            })


        }


    }


    override fun onDestroyView() {
        (mapView?.parent as? ViewGroup)?.removeView(mapView)
        mapView = null
        super.onDestroyView()
    }
}