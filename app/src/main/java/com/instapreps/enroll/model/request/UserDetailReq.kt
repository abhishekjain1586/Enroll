package com.instapreps.enroll.model.request

import com.google.gson.annotations.SerializedName

class UserDetailReq {

    @SerializedName("fullname")
    var fullname : String? = null

    @SerializedName("mobileno")
    var mobileno : String? = null;

    @SerializedName("emailid")
    var emailid : String? = null;

    @SerializedName("message")
    var message : String? = null;

}