package com.faceki.utils

import com.google.gson.annotations.SerializedName
import java.io.Serializable

enum class StatusCodes(val value:Int):Serializable {
    @SerializedName("1000")
    INTERNAL_SYSTEM_ERROR(1000),
    @SerializedName("0")
    SUCCESS(0),
    @SerializedName("7001")
    NO_RULES_FOR_COMPANY(7001),
    @SerializedName("8001")
    NEED_REQUIRED_IMAGES(8001),
    @SerializedName("8002")
    DOCUMENT_VERIFY_FAILED(8002),
    @SerializedName("8003")
    PLEASE_TRY_AGAIN(8003),
    @SerializedName("8004")
    FACE_CROPPED(8004),
    @SerializedName("8005")
    FACE_TOO_CLOSED(8005),
    @SerializedName("8006")
    FACE_NOT_FOUND(8006),
    @SerializedName("8007")
    FACE_CLOSED_TO_BORDER(8007),
    @SerializedName("8008")
    FACE_TOO_SMALL(8008),
    @SerializedName("8009")
    POOR_LIGHT(8009),
    @SerializedName("8010")
    ID_VERIFY_FAIL(8010),
    @SerializedName("8011")
    DL_VERIFY_FAIL(8011),
    @SerializedName("8012")
    PASSPORT_VERIFY_FAIL(8012),
    @SerializedName("8013")
    DATA_NOT_FOUND(8013),
    @SerializedName("8014")
    INVALID_VERIFICATION_LINK(8014),
    @SerializedName("8015")
    VERIFICATION_LINK_EXPIRED(8015),
    @SerializedName("8016")
    FAIL_TO_GENERATE_LINK(8016),
    @SerializedName("8017")
    KYC_VERIFICATION_LIMIT_REACHED(8017),
    @SerializedName("8018")
    SELFIE_MULTIPLE_FACES(8018),
    @SerializedName("8019")
    FACE_BLURR(8019),
    @SerializedName("8020")
    MISSING_ID_CARD_DOCUMENT_WITH_RULES(8020),
    @SerializedName("8021")
    MISSING_PASSPORT_DOCUMENT_WITH_RULES(8021),
    @SerializedName("8022")
    MISSING_DL_DOCUMENT_WITH_RULES(8022),
    UNKNOWN(-1);

    companion object {
        fun getStatusCodeFromInt(value: Int) = StatusCodes.values().first { it.value == value }
    }
}
