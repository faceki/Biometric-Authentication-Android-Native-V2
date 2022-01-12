package com.faceki.response

data class LoginResponse(
    val packageInfo: PackageInfo,
    val user: User
) {
    data class PackageInfo(
        val record: List<Record>
    ) {
        data class Record(
            val __v: Int,
            val _id: String,
            val api_transactions: Int,
            val assigned_api_names: String,
            val client_id: String,
            val createdAt: String,
            val duration: String,
            val expiry_date: String,
            val monthly_quota: Int,
            val package_id: String,
            val payment_status: Boolean,
            val updatedAt: String
        )
    }

    data class User(
        val __v: Int,
        val _id: String,
        val active: Int,
        val client_id: String,
        val confidence: Int,
        val createdAt: String,
        val email: String,
        val face_id: String,
        val image_id: String,
        val mobile_number: Long,
        val name: String,
        val updatedAt: String
    )
}
