package com.faceki.network


import com.faceki.network.RestClient.client
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ApiCall<Req, Res> private constructor() {
    /**
     * Method for login the user.
     *
     * @param req
     * @param iApiCallback
     */


    companion object {
        var apiCall: ApiCall<*, *>? = null
        var service: IRestInterfaces? = null
        val instance: ApiCall<*, *>?
            get() {
                if (apiCall == null) {
                    apiCall = ApiCall<Any?, Any?>()
                    service = client
                }
                return apiCall
            }
    }

    fun getToken(map: HashMap<String, String>, iApiCallback: IApiCallback) {
        val call: Call<Any> = service!!.getToken(map)
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, responseGet: Response<Any?>) {
                iApiCallback.onSuccess("GetToken", responseGet)
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                iApiCallback.onFailure(t.message)
            }
        })
    }

    fun login(token: String, image: MultipartBody.Part, iApiCallback: IApiCallback) {
        val call: Call<Any> = service!!.login(token, image)
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, responseGet: Response<Any?>) {
                iApiCallback.onSuccess("Login", responseGet)
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                iApiCallback.onFailure(t.message)
            }
        })
    }

    fun signup(token: String, image: MultipartBody.Part, map: MutableMap<String, RequestBody>, iApiCallback: IApiCallback) {
        val call: Call<Any> = service!!.signup(token, image, map)
        call.enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>, responseGet: Response<Any?>) {
                iApiCallback.onSuccess("SignUp", responseGet)
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                iApiCallback.onFailure(t.message)
            }
        })
    }

}