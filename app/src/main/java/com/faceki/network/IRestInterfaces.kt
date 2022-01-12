package com.faceki.network

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

interface IRestInterfaces {

    @FormUrlEncoded
    @POST("getToken")
    fun getToken(@FieldMap fields: HashMap<String, String>): Call<Any>

    @Multipart
    @POST("login")
    fun login(@Header("Authorization") token: String, @Part image: MultipartBody.Part): Call<Any>

    @Multipart
    @POST("signup")
    fun signup(
        @Header("Authorization") token: String,
        @Part image: MultipartBody.Part,
        @PartMap fields: MutableMap<String, RequestBody>
    ): Call<Any>
}