package com.example.allgoing.retrofit

import com.example.allgoing.retrofit.DTO.Request.CatAssChangeReq
import com.example.allgoing.retrofit.DTO.Request.LoginReq
import com.example.allgoing.retrofit.DTO.Request.PostNewReq
import com.example.allgoing.retrofit.DTO.Request.ReviewCreateReq
import com.example.allgoing.retrofit.DTO.Response.BoardListRes
import com.example.allgoing.retrofit.DTO.Response.CatAssListRes
import com.example.allgoing.retrofit.DTO.Response.CatAssChangeRes
import com.example.allgoing.retrofit.DTO.Response.CatExpRes
import com.example.allgoing.retrofit.DTO.Response.LoginRes
import com.example.allgoing.retrofit.DTO.Response.ReservationDeleteRes
import com.example.allgoing.retrofit.DTO.Response.ReservationMyRes
import com.example.allgoing.retrofit.DTO.Response.ReservationProductRes
import com.example.allgoing.retrofit.DTO.Response.ReservationStoreListRes
import com.example.allgoing.retrofit.DTO.Response.ReservationVisitedRes
import com.example.allgoing.retrofit.DTO.Response.ReviewCreateRes
import com.example.allgoing.retrofit.DTO.Response.ReviewDeleteRes
import com.example.allgoing.retrofit.DTO.Response.ReviewDetailRes
import com.example.allgoing.retrofit.DTO.Response.ReviewDisLikeRes
import com.example.allgoing.retrofit.DTO.Response.ReviewLikeListRes
import com.example.allgoing.retrofit.DTO.Response.ReviewLikeRes
import com.example.allgoing.retrofit.DTO.Response.ReviewMineRes
import com.example.allgoing.retrofit.DTO.Response.ReviewTraditionalRes
import com.example.allgoing.retrofit.DTO.Response.ShopCoinRes
import com.example.allgoing.retrofit.DTO.Response.ShopItemListRes
import com.example.allgoing.retrofit.DTO.Response.StoreAllListRes
import com.example.allgoing.retrofit.DTO.Response.StoreHomeRes
import com.example.allgoing.retrofit.DTO.Response.StoreFastListRes
import com.example.allgoing.retrofit.DTO.Response.StoreNoticeRes
import com.example.allgoing.retrofit.DTO.Response.StoreReviewRes
import com.example.allgoing.retrofit.DTO.Response.TraditionalListRes
import com.example.allgoing.retrofit.DTO.Response.TraditionalStoreListRes
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {

    //로그인
    @POST("/auth/idTokenLogin")
    fun postLogin(
        @Body request: LoginReq
    ): Call<LoginRes>

    //로그아웃
    @POST("/auth/logout")
    fun postLogout(
        @Header("authorization") authorization : String?,
        @Body request:
    ): Call<>

    // 회원 탈퇴
    @DELETE("/auth/exit")
    fun deleteUser(
        @Header("authorization") authorization : String?
    ): Call<ReviewDeleteRes>


    //사용자

    //닉네임 및 이메일 정보 수정
    @PATCH("/api/v1/user/profile")
    fun patchUserProfile(
        @Body request: PinModifyRequest
    ): Call<CommentFindReponse>

    //닉네임 및 이메일 정보 조회 --> X
    @GET("/api/v1/user/profile")
    fun getUserProfile(
    ): Call<CommentFindReponse>

    //닉네임 중복 확인 --> X
    @POST("/api/v1/user/nickname")
    fun postUserNickname(
        @Body request: PinModifyRequest
    ): Call<>


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
    @PATCH("/api/v1/cat/item")
    fun patchCatAss(
        @Header("authorization") authorization: String,
        @Body request: CatAssChangeReq
    ): Call<CatAssChangeRes>

    //아이텝샵 - 카테고리 별 아이템 목록 조회
    @GET("/api/v1/shop/list")
    fun getShopItemList(
        @Header("authorization") authorization: String
    ): Call<ShopItemListRes>

    //아이템 구매
    @POST("/api/v1/shop/{itemId}")
    fun postUserNickname(
        @Header("authorization") authorization: String,
        @Path(value = "itemId") itemId: Int,
        @Body request: PinModifyRequest
    ): Call<>

    //코인 보유 수 조회
    @GET("/api/v1/shop/coin")
    fun getShopCoin(
        @Header("authorization") authorization: String
    ): Call<ShopCoinRes>


    //게시판

    //게시판 글 작성 --X
    @POST("/api/v1/post/new")
    fun postWrite(
        @Header("authorization") authorization: String,
        @Body request: PostNewReq
    ): Call<>

    //게시판 목록 조회 -> X
    @GET("/api/v1/post/list")
    fun getBoardList(
        @Header("authorization") authorization: String
    ): Call<BoardListRes>

    //게시판 글 상세 조회 -> X
    @GET("/api/v1/post/{postId}")
    fun getBoardDetailList(
        @Header("authorization") authorization: String,
        @Path(value = "postId") postId: Int
    ): Call<>

    //게시글 삭제 --> X
    @DELETE("/api/v1/post/{postId}")
    fun deleteBoard(
        @Header("authorization") authorization: String,
        @Path("postId") postId: Int
    ): Call<>

    //게시글 댓글 작성 --> X
    @POST("/api/v1/post/{postId}/comment")
    fun postComment(
        @Header("authorization") authorization: String,
        @Path(value = "postId") postId: Int,
        @Body request:
    ): Call<>

    //게시판 댓글 삭제
    @DELETE("/api/v1/post/{postId}/{commentId}")
    fun deleteComment(
        @Header("authorization") authorization: String,
        @Path("postId") postId: Int,
        @Path("commentId") commentId: Int
    ): Call<>

    //게시판 좋아요 수정 -> X
    @POST("/api/v1/post/{postId}/like")
    fun postBoardLikeChange(
        @Header("authorization") authorization: String,
        @Path(value = "postId") postId: Int,
        @Body request:
    ): Call<>

    //내가 쓴 게시글 목록 조회 -> X
    @GET("/api/v1/post/my/list")
    fun getMyBoardList(
        @Header("authorization") authorization: String
    ): Call<>

    //좋아요 누른 게시글 목록 조회 -> X
    @GET("/api/v1/post/my/like")
    fun getMyLikeList(
        @Header("authorization") authorization: String
    ): Call<>

    //댓글 단 게시글 목록 조회 -> X
    @GET("/api/v1/post/my/comment")
    fun getMyCommentList(
        @Header("authorization") authorization: String
    ): Call<>

    //store

    //가게 정보 전체 조회
    @GET("/api/v1/store/allsummaries")
    fun getStoreAllList(
    ): Call<StoreAllListRes>

    //가게 정보 간단 조회 //핀 클릭 시
    @GET("/api/v1/store/summary/{storeId}")
    fun getStoreList(
        @Path(value = "storeId") storeId: Int
    ): Call<StoreFastListRes>

    //가게 홈 조회
    @GET("/api/v1/store/home/{storeId}")
    fun getStoreHome(
        @Path(value = "storeId") storeId: Int
    ): Call<StoreHomeRes>

    //가게 소식 조회
    @GET("/api/v1/store/notice/{storeId}")
    fun getStoreNotice(
        @Path(value = "storeId") storeId: Int
    ): Call<StoreNoticeRes>

    //가게 리뷰 조회
    @GET("/api/v1/store/review/{storeId}")
    fun getStoreReview(
        @Path(value = "storeId") storeId: Int
    ): Call<StoreReviewRes>



    //리뷰

    //리뷰 작성
    @POST("/api/v1/review/create/{storeId}")
    fun getReviewCreate(
        @Header("authorization") authorization: String,
        @Path("storeId") storeId: Int,
        @Body request: ReviewCreateReq
    ): Call<ReviewCreateRes>

    //리뷰 삭제 -> X
    @DELETE("/api/v1/review/delete/{reviewId}")
    fun deleteReview(
        @Header("authorization") authorization: String,
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
    ): Call<ReviewMineRes>

    //리뷰 좋아요 누르기
    @POST("/api/v1/review/like/{reviewId}")
    fun getReviewLike(
        @Header("authorization") authorization: String,
        @Path(value = "reviewId") reviewId: Int,
    ): Call<ReviewLikeRes>

    //리뷰 좋아요 삭제
    @DELETE("/api/v1/review/like/{reviewId}")
    fun deleteReviewLike(
        @Header("authorization") authorization: String,
        @Path("reviewId") reviewId: Int
    ): Call<ReviewDisLikeRes>

    //좋아요 한 리뷰 모두 보기
    @GET("/api/v1/review/like")
    fun getReviewLikeList(
        @Header("authorization") authorization: String
    ): Call<ReviewLikeListRes>

    //전통시장 리뷰 모두 보기
    @GET("/api/v1/review/traditional/{traditionalId}")
    fun getReviewTraditional(
        @Header("authorization") authorization: String,
        @Path("traditionalId") traditionalId: Int
    ): Call<ReviewTraditionalRes>

    //모든 전통시장 정보 보기
    @GET("/api/v1/traditional")
    fun getTraditionalList(
    ): Call<TraditionalListRes>

    //해당 시장에 포함된 가게
    @GET("/api/v1/traditional/{traditionalId}")
    fun getTraditionalStoreList(
        @Path("traditionalId") traditionalId: Int
    ): Call<TraditionalStoreListRes>

    //예약

    //해당 가게 예약 현황 조회
    @GET("/api/v1/reservation/store/{storeId}")
    fun getReservationStoreList(
        @Path("storeId") storeId: Int
    ): Call<ReservationStoreListRes>

    //해당 가게 상품 조회
    @GET("/api/v1/reservation/product/{storeId}")
    fun getReservationProductList(
        @Path(value = "storeId") storeId: Int
    ): Call<ReservationProductRes>

    //예약하기 --X
    @POST("/api/v1/reservation/{storeId}")
    fun PostReservation(
        @Header("authorization") authorization: String,
        @Path(value = "storeId") storeId: Int,
        @Body request: PinModifyRequest
    ): Call<ReviewLikeRes>

    // 예약 취소
    @PATCH("/api/v1/reservation/{reservationId}")
    fun patchReservation(
        @Header("authorization") authorization: String,
        @Path(value = "reservationId") reservationId: Int
    ): Call<ReservationDeleteRes>

    //방문 완료 후 예약 완료 처리 -
    @POST("/api/v1/reservation/visited/{reservationId}")
    fun PostReservationVistied(
        @Header("authorization") authorization: String,
        @Path(value = "reservationId") reservationId: Int
    ): Call<ReservationVisitedRes>

    //내 예약 내역 조회
    @GET("/api/v1/reservation/my")
    fun getReservationMy(
        @Header("authorization") authorization: String
    ): Call<ReservationMyRes>

}