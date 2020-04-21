package com.instapreps.enroll.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.instapreps.enroll.model.request.UserDetailReq
import com.instapreps.enroll.model.response.UserDetailRes
import com.instapreps.enroll.repository.UserEnrollRepository

class MainActivityViewModel : ViewModel() {

    private var userEnrollRepository = UserEnrollRepository()
    private var userData = UserDetailReq()

    fun enroll() : LiveData<UserDetailRes> {
        return userEnrollRepository.enrollUser(userData)
    }

    fun setMobileNumber(mobileNo : String) {
        userData.mobileno = mobileNo
    }

    fun setFullName(fullName : String) {
        userData.fullname = fullName
    }

    fun setEmail(email : String) {
        userData.emailid = email
    }

    fun setMessage(message : String) {
        userData.message = message
    }

}