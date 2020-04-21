package com.instapreps.enroll.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.TextUtilsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.instapreps.enroll.R
import com.instapreps.enroll.model.request.UserDetailReq
import com.instapreps.enroll.model.response.UserDetailRes
import com.instapreps.enroll.view.activity.MainActivity
import com.instapreps.enroll.view.base.BaseFragment
import com.instapreps.enroll.viewmodel.MainActivityViewModel

class UserMobileNoFragment : BaseFragment(), View.OnClickListener {

    lateinit var viewModel : MainActivityViewModel
    lateinit var btnContinue : Button
    lateinit var edtMobileNo : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseView = inflater.inflate(R.layout.layout_mobileno, container, false);
        return baseView
    }

    override fun initViews() {
        edtMobileNo = baseView.findViewById(R.id.edt_mobileno_value)
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

    fun callNameFragment() {
        viewModel.setMobileNumber(edtMobileNo.text.toString())
        (activity as MainActivity).addFragment(UserNameFragment())
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_continue -> {
                if (validate()) {
                    callNameFragment()
                }
            }
        }
    }

    fun validate() : Boolean {
        if (!edtMobileNo.text.toString().trim().isNullOrEmpty() && edtMobileNo.text.toString().trim().length == 10) {
            return true
        }
        Toast.makeText(requireContext(), "Enter valid Number", Toast.LENGTH_LONG).show()
        return false
    }
}