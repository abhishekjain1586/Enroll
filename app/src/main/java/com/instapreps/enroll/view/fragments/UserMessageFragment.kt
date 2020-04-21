package com.instapreps.enroll.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.instapreps.enroll.R
import com.instapreps.enroll.model.response.UserDetailRes
import com.instapreps.enroll.view.activity.MainActivity
import com.instapreps.enroll.view.base.BaseFragment
import com.instapreps.enroll.viewmodel.MainActivityViewModel
import com.locationtracker.myapplication.utils.NetworkUtil

class UserMessageFragment : BaseFragment(), View.OnClickListener {

    lateinit var viewModel : MainActivityViewModel
    lateinit var edtMessage : EditText
    lateinit var btnContinue : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseView = inflater.inflate(R.layout.layout_message, container, false);
        return baseView
    }

    override fun initViews() {
        edtMessage = baseView.findViewById(R.id.edt_message_value)
        btnContinue = baseView.findViewById(R.id.btn_continue)
        btnContinue.setOnClickListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
    }

    fun initViewModel() {
        viewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
    }

    fun callMobileNoFragment() {
        viewModel.setMessage(edtMessage.text.toString())
        viewModel.enroll().observe(this, object : Observer<UserDetailRes> {
            override fun onChanged(userResponse: UserDetailRes?) {
                (activity as MainActivity).showDialog("Success", "Enrolled Successfully", true)
            }
        })
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_continue -> {
                if (NetworkUtil.isNetworkConnected(requireActivity())) {
                    if (validate()) {
                        callMobileNoFragment()
                    }
                } else {
                    Toast.makeText(activity, "Please check your Network Connection", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    fun validate() : Boolean {
        if (!edtMessage.text.toString().trim().isNullOrEmpty()) {
            return true
        }
        Toast.makeText(requireContext(), "Enter Message", Toast.LENGTH_LONG).show()
        return false
    }
}