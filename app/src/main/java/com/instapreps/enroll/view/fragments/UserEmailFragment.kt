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

class UserEmailFragment : BaseFragment(), View.OnClickListener {

    lateinit var viewModel : MainActivityViewModel
    lateinit var edtEmail : EditText
    lateinit var btnContinue : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseView = inflater.inflate(R.layout.layout_email, container, false);
        return baseView
    }

    override fun initViews() {
        edtEmail = baseView.findViewById(R.id.edt_email_value)
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

    fun callMessageFragment() {
        viewModel.setEmail(edtEmail.text.toString())
        (activity as MainActivity).addFragment(UserMessageFragment())
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_continue -> {
                if (validate()) {
                    callMessageFragment()
                }
            }
        }
    }

    fun validate() : Boolean {
        if (!edtEmail.text.toString().trim().isNullOrEmpty()) {
            return true
        }
        Toast.makeText(requireContext(), "Enter valid Email", Toast.LENGTH_LONG).show()
        return false
    }
}