package com.instapreps.enroll.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.abhishekjain.mvvmkotlindemo.service.ServiceHelper
import com.instapreps.enroll.model.request.UserDetailReq
import com.instapreps.enroll.model.response.UserDetailRes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserEnrollRepository {

    fun enrollUser(userDetailReq: UserDetailReq) : LiveData<UserDetailRes> {
        var resLiveData = MutableLiveData<UserDetailRes>()

        var callback = ServiceHelper.getRetrofitClient().enrollUser(userDetailReq)
        callback.enqueue(object : Callback<UserDetailRes> {
            override fun onResponse(call: Call<UserDetailRes>, response: Response<UserDetailRes>) {
                if (response.body() != null) {
                    resLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<UserDetailRes>, t: Throwable) {
                resLiveData.value = null
            }
        })
        return resLiveData
    }
}