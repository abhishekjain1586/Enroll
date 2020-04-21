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

class UserNameFragment : BaseFragment(), View.OnClickListener {

    lateinit var viewModel : MainActivityViewModel
    lateinit var btnContinue : Button
    lateinit var edtFullName : EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        baseView = inflater.inflate(R.layout.layout_fullname, container, false);
        return baseView
    }

    override fun initViews() {
        edtFullName = baseView.findViewById(R.id.edt_fullname_value)
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

    fun callEmailFragment() {
        viewModel.setFullName(edtFullName.text.toString())
        (activity as MainActivity).addFragment(UserEmailFragment())
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.btn_continue -> {
                if (validate()) {
                    callEmailFragment()
                }
            }
        }
    }

    fun validate() : Boolean {
        if (!edtFullName.text.toString().trim().isNullOrEmpty()) {
            return true
        }
        Toast.makeText(requireContext(), "Enter valid full Name", Toast.LENGTH_LONG).show()
        return false
    }
}