package com.example.allgoing.retrofit

import com.example.allgoing.retrofit.DTO.Request.CatAssChangeReq
import com.example.allgoing.retrofit.DTO.Request.LoginReq
import com.example.allgoing.retrofit.DTO.Request.ReviewCreateReq
import com.example.allgoing.retrofit.DTO.Response.CatAssListRes
import com.example.allgoing.retrofit.DTO.Response.CatAssChangeRes
import com.example.allgoing.retrofit.DTO.Response.CatExpRes
import com.example.allgoing.retrofit.DTO.Response.LoginRes
import com.example.allgoing.retrofit.DTO.Response.ReviewCreateRes
import com.example.allgoing.retrofit.DTO.Response.ReviewDeleteRes
import com.example.allgoing.retrofit.DTO.Response.ReviewDetailRes
import com.example.allgoing.retrofit.DTO.Response.ReviewLikeRes
import com.example.allgoing.retrofit.DTO.Response.ReviewMinRes
import com.example.allgoing.retrofit.DTO.Response.StoreHomeRes
import com.example.allgoing.retrofit.DTO.Response.StoreAllListRes
import com.example.allgoing.retrofit.DTO.Response.StoreFastListRes
import com.example.allgoing.retrofit.DTO.Response.StoreNoticeRes
import com.example.allgoing.retrofit.DTO.Response.StoreReviewRes
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    @POST("/auth/idTokenLogin")
    fun postLogin(
        @Body request: LoginReq
    ): Call<LoginRes>


    //shop

//    //아이템 구매
//    @POST("/api/v1/shop/{ItemId}")
//    fun postShopItemBuy(
//        @Path(value = "ItemId") itemId: Int,
//    ): Call<ShopItemBuyRes>
//
//    // 카테고리별 아이템 목록 조회
//    @GET("/api/v1/shop/list")
//    fun getShopItemList(
//        @Header("authorization") authorization : String?
//    ): Call<ShopItemListRes>
//
//    // 보유 코인 정보 조회
//    @GET("/api/v1/shop/coin")
//    fun getShopItemCoin(
//        @Header("authorization") authorization : String?
//    ): Call<ShopItemCoin>

    // store

    //가게 정보 전체 조회
    @GET("/api/v1/store/allsummaries")
    fun getStoreAllList(
        @Header("authorization") authorization : String?
    ): Call<StoreAllListRes>

    //가게 정보 간단 조회 //핀 클릭 시
    @GET("/api/v1/store/summary/{storeId}")
    fun getStoreList(
        @Path(value = "storeId") storeId: Int,
    ): Call<StoreFastListRes>

    //가게 리뷰 조회
    @GET("/api/v1/store/review/{storeId}")
    fun getStoreReview(
        @Path(value = "storeId") storeId: Int,
    ): Call<StoreReviewRes>

    //가게 소식 조회
    @GET("/api/v1/store/notice/{storeId}")
    fun getStoreNotice(
        @Path(value = "storeId") storeId: Int,
    ): Call<StoreNoticeRes>

    //가게 홈 조회
    @GET("/api/v1/review/home/{storeId}")
    fun getStoreHome(
        @Header("authorization") authorization: String,
        @Path(value = "storeId") storeId: Int,
    ): Call<StoreHomeRes>

    //리뷰

    //리뷰 작성 ->  X
    @GET("/api/v1/review/create/{reviewId}")
    fun getReviewCreate(
        @Header("authorization") authorization: String,
        @Path("reviewId") reviewId: Int,
        @Body request: ReviewCreateReq
    ): Call<ReviewCreateRes>

    //리뷰 삭제 -> X
    @DELETE("/api/v1/review/delete/{reviewId}")
    fun deleteReview(
        @Path("reviewId") reviewId: Int
    ): Call<ReviewDeleteRes>

    //리뷰 상세 보기
    @GET("/api/v1/review/detail/{reviewId}")
    fun getReviewDetail(
        @Header("authorization") authorization: String,
        @Path(value = "reviewId") reviewId: Int,
    ): Call<ReviewDetailRes>

    //내가 쓴 리뷰 보기
    @GET("/api/v1/review/myreview")
    fun getReviewMine(
        @Header("authorization") authorization: String
    ): Call<ReviewMinRes>

    //리뷰 좋아요 누르기
    @POST("/api/v1/review/like/{reviewId}")
    fun getReviewLike(
        @Header("authorization") authorization: String,
        @Path(value = "reviewId") reviewId: Int,
    ): Call<ReviewLikeRes>

//    //리뷰 좋아요 삭제 -> X
//    @DELETE("/api/v1/review/like/{reviewId}")
//    fun deleteReviewLike(
//        @Path("reviewId") reviewId: Int
//    ): Call<>


    // 고양이

    //경험치 및 레벨 조회
    @GET("/api/v1/cat/exp")
    fun getCatExp(
        @Header("authorization") authorization: String
    ): Call<CatExpRes>

    //착용 악세서리 목록 조회
    @GET("/api/v1/cat/my")
    fun getCatAssList(
        @Header("authorization") authorization: String
    ): Call<CatAssListRes>

    //착용 악세서리 수정
    @PATCH("/user/hot-change")
    fun patchCatAss(
        @Body request: CatAssChangeReq
    ): Call<CatAssChangeRes>

}