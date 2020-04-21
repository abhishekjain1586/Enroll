package com.instapreps.enroll.model.response

import com.google.gson.annotations.SerializedName

class UserDetailRes {

    @SerializedName("success")
    var success : String? = null

    @SerializedName("message")
    var message : String? = null

}