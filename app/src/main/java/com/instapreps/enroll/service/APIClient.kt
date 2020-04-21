package com.example.abhishekjain.mvvmkotlindemo.service

import com.instapreps.enroll.model.request.UserDetailReq
import com.instapreps.enroll.model.response.UserDetailRes
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface APIClient {

    @POST("/api/enrollnow")
    fun enrollUser(@Body userDetailReq: UserDetailReq) : Call<UserDetailRes>
}