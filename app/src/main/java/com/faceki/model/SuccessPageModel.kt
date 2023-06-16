package com.faceki.model

import java.io.Serializable

class SuccessPageModel(
    val responseCode: Int?,
    val data: Data?,
    val type: String
) : Serializable {
    data class Data(
        val logedIn: Boolean?,
        val message: String?,
        val user: User?,
    ): Serializable{
        data class User(
            val _id: String?,
            val userId: String?,
            val selfieImage: String?,
            val imageID: String?,
            val faceID: String?,
            val email: String?,

        ):Serializable
    }

}
