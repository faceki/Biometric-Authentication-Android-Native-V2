package com.faceki.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface IRestInterfaces {

    @GET("auth/api/access-token")
    fun getToken(@Query("clientId") clientId: String, @Query("clientSecret") clientSecret: String): Call<Any>

    @Multipart
    @POST("facelink/api/login")
    fun login(@Header("Authorization") token: String, @Part image: MultipartBody.Part): Call<Any>

    @Multipart
    @POST("facelink/api/signup")
    fun signup(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @PartMap fields: MutableMap<String, RequestBody>
    ): Call<Any>
}
