package com.example.allgoing.fragment

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.text.isDigitsOnly
import androidx.fragment.app.Fragment
import com.example.allgoing.R
import com.example.allgoing.activity.MainActivity
import com.example.allgoing.databinding.FragmentMapBinding
import com.example.allgoing.retrofit.DTO.Response.StoreAllListRes
import com.example.allgoing.retrofit.DTO.Response.StoreFastListRes
import com.example.allgoing.retrofit.DTO.Response.TraditionalListRes
import com.example.allgoing.retrofit.DTO.Response.TraditionalStoreListRes
import com.example.allgoing.retrofit.RetrofitClient
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.kakao.vectormap.GestureType
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMap.OnCameraMoveEndListener
import com.kakao.vectormap.KakaoMap.OnLabelClickListener
import com.kakao.vectormap.KakaoMap.OnMapViewInfoChangeListener
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.KakaoMapSdk
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapGravity
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraPosition
import com.kakao.vectormap.label.Label
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.internal.ILabelDelegate
import com.kakao.vectormap.label.LabelLayer
import com.kakao.vectormap.label.LabelLayerOptions
import com.kakao.vectormap.label.LabelManager
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kakao.vectormap.label.OnLabelCreateCallback
import com.kakao.vectormap.mapwidget.MapWidgetOptions
import com.kakao.vectormap.mapwidget.component.GuiImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.reflect.typeOf

class MapFragment : Fragment(){
    lateinit var binding: FragmentMapBinding
    private var mapView: MapView? = null

    private var kakaoMap : KakaoMap? = null
    private var userWidget : GuiImage? = null

//    var storeId: Int = 1
//    var traditionalId : Int = 1

    private lateinit var fusedLocationClient: FusedLocationProviderClient

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


        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

        initMap()

        return binding.root
    }

    private fun initMap() {
        KakaoMapSdk.init(requireContext(), "6464ccc167d7d34887af472c191a971f")
        mapView = binding.kakaoMapView
        binding.kakaoMapView.setOnClickListener{
            binding.mapBottom.visibility = View.GONE
        }

        if (mapView != null) {

            mapView!!.start(
                object : MapLifeCycleCallback() {

                    override fun onMapDestroy() {
                        Log.e("MapFragment", "Destroy to initialize MapView")
                        initMap()
                    }

                    override fun onMapError(p0: Exception?) {
                        Log.e("MapFragment", "Failed to initialize MapView")
                        initMap()
                    }

                },
                object : KakaoMapReadyCallback() {
                    override fun onMapReady(map: KakaoMap) {
                        Log.d("MapFragment", "MapView initialized successfully")

                        kakaoMap = map
                        position

                        map.setOnCameraMoveEndListener(object : OnCameraMoveEndListener{
                            override fun onCameraMoveEnd(
                                p0: KakaoMap,
                                p1: CameraPosition,
                                p2: GestureType
                            ) {
                                setStore()
                            }

                        })


                    }

                    override fun getPosition(): LatLng {
                        // FusedLocationProviderClient 초기화
                        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
                        var loc = LatLng.from(37.5665, 126.9780)

                        // 위치 권한 요청
                        requestLocationPermission()

                        if (ActivityCompat.checkSelfPermission(
                                requireContext(),
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                                if (location != null) {
                                    // 위치 정보 사용
                                    val latitude = location.latitude
                                    val longitude = location.longitude
                                    Log.d("MapFragment", "위도: $latitude, 경도: $longitude")

                                    loc = LatLng.from(location.latitude,location.longitude)

                                    kakaoMap?.moveCamera(CameraUpdateFactory.newCenterPosition(loc))
                                    initCat(loc)
                                } else {
                                    Log.d("MapFragment", "위치를 가져올 수 없습니다.")
                                }
                            }

                            Log.d("MapFragment","position2 "+loc.toString())
                            return loc
                        }
                        else {
                            Log.d("MapFragment","position3 "+loc.toString())
                            return loc
                        }
                    }
                }
            )
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity as MainActivity,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1000
            )
        }
    }

    private fun initCat(loc:LatLng) {

        val styles = kakaoMap?.labelManager?.addLabelStyles(LabelStyles.from(LabelStyle.from(R.drawable.img_cat_top)))
        val options = LabelOptions.from(loc).setTag("cat").setStyles(styles)

        val layer = kakaoMap?.labelManager?.getLayer()

        layer?.addLabel(options)

    }


    private fun setStore() {
        var res : ArrayList<TraditionalListRes.Information> = arrayListOf()

        RetrofitClient.service.getTraditionalList(MainActivity.accessToken).enqueue(object : Callback<TraditionalListRes> {
            override fun onResponse(
                call: Call<TraditionalListRes>,
                response: Response<TraditionalListRes>
            ) {
                Log.d("MapFragment api 1",response.body().toString())
                Log.d("MapFragment api 2",response.toString())
                Log.d("MapFragment api 3",MainActivity.accessToken)
                if (response.body() != null) {
                    res = response.body()!!.information

                    kakaoMap?.labelManager?.addLayer(LabelLayerOptions.from("store"))
                    val layer = kakaoMap?.labelManager?.getLayer("store")

                    val styles = LabelStyles.from(LabelStyle.from(R.drawable.img_shop_pin))

                    val storeStyles = kakaoMap?.labelManager?.addLabelStyles(styles)

                    for(index in 0..(res.size-1)) {
                        Log.d("MapFragment", "store"+index.toString()+ "")

                        var loc = LatLng.from(res[index].traditionalLatitude.toDouble(),res[index].traditionalLongitude.toDouble())
                        val options = LabelOptions.from("store"+index.toString(),loc).setTag(index).setStyles(storeStyles).setTag("store")

                        layer?.addLabel(options)
                        setStoreList(res[index].traditionalId)
//                            , object : OnLabelCreateCallback{
//                                override fun onLabelCreated(layer: LabelLayer?, label: Label?) {
//                                    if (label != null) {
//                                        Log.d("MapFragment", "store"+index.toString()+ "" + loc.toString())
//                                        kakaoMap?.setOnLabelClickListener(object : OnLabelClickListener{
//                                            override fun onLabelClicked(
//                                                kakaoMap: KakaoMap,
//                                                layer: LabelLayer?,
//                                                label: Label
//                                            ): Boolean {
//                                                if (label.tag != "cat"){
//                                                    binding.mapBottom.visibility = View.VISIBLE
//                                                }
//
//                                                return true
//                                            }
//                                        })
//                                    }
//                                }
//
//                            })
                    }
                }
            }

            override fun onFailure(call: Call<TraditionalListRes>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    //가게
    private fun setStoreList(traditionalId: Int) {
        var res: ArrayList<TraditionalStoreListRes.Information> = arrayListOf()

        RetrofitClient.service.getTraditionalStoreList(MainActivity.accessToken, traditionalId)
            .enqueue(object : Callback<TraditionalStoreListRes> {
                override fun onResponse(
                    call: Call<TraditionalStoreListRes>,
                    response: Response<TraditionalStoreListRes>
                ) {
                    Log.d("MapFragment api 1", response.body().toString())
                    Log.d("MapFragment api 2", response.toString())
                    Log.d("MapFragment api 3", MainActivity.accessToken)

                    if (response.body() != null) {
                        res = response.body()!!.information

                        kakaoMap?.labelManager?.addLayer(LabelLayerOptions.from("$traditionalId"))
                        val layer = kakaoMap?.labelManager?.getLayer("$traditionalId")

                        val styles = LabelStyles.from(LabelStyle.from(R.drawable.img_store_pin))

                        val storeStyles = kakaoMap?.labelManager?.addLabelStyles(styles)

                        for (index in res.indices) {
                            Log.d("MapFragment", "shop$index")

                            val loc = LatLng.from(
                                res[index].storeLatitude,
                                res[index].storeLongitude
                            )
                            var temp = res[index].storeId
                            val options = LabelOptions.from("$temp", loc)
                                .setStyles(storeStyles).setTag("shop")

                            layer?.addLabel(options, object : OnLabelCreateCallback{
                                override fun onLabelCreated(p0: LabelLayer?, p1: Label?) {
                                    kakaoMap?.setOnLabelClickListener(object : OnLabelClickListener {
                                        override fun onLabelClicked(
                                            kakaoMap: KakaoMap,
                                            layer: LabelLayer?,
                                            label: Label
                                        ): Boolean {
                                            if (true) {
                                                var shopId = label.labelId.toInt()

                                                Log.d("tttttaaa", shopId.toString())

                                                RetrofitClient.service.getStoreList(MainActivity.accessToken,shopId).enqueue(object : Callback<StoreFastListRes>{
                                                    override fun onResponse(
                                                        call: Call<StoreFastListRes>,
                                                        response: Response<StoreFastListRes>
                                                    ) {

                                                        Log.d("ttttt", response.toString() + " " + shopId.toString())
                                                        Log.d("ttttt", response.body().toString() + " " + shopId.toString())
                                                        if (response.body()?.information != null) {

                                                            val info = response.body()!!.information

                                                            binding.mapBottom.visibility = View.VISIBLE
                                                            binding.mapBottomTitle.text = info.storeName
                                                            binding.mapBottomContent.text= info.storeIntro
                                                            binding.mapBottomRate.text = info.star.toString()
                                                            binding.mapBottomReview.text = "리뷰 +${info.reviewCount}"

                                                        }
                                                    }

                                                    override fun onFailure(
                                                        call: Call<StoreFastListRes>,
                                                        t: Throwable
                                                    ) {
                                                        TODO("Not yet implemented")
                                                    }

                                                })
                                            }
                                            return true
                                        }
                                    })
                                }


                            })

                        }
                    }
                }

                override fun onFailure(call: Call<TraditionalStoreListRes>, t: Throwable) {
                    Log.e("MapFragment", "API call failed: ${t.message}")
                }
            })
    }



    override fun onDestroyView() {
        (mapView?.parent as? ViewGroup)?.removeView(mapView)
        mapView = null
        super.onDestroyView()
    }
}