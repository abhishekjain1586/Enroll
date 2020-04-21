package com.instapreps.enroll.view.activity

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.instapreps.enroll.R
import com.instapreps.enroll.model.request.UserDetailReq
import com.instapreps.enroll.model.response.UserDetailRes
import com.instapreps.enroll.view.fragments.UserMobileNoFragment
import com.instapreps.enroll.viewmodel.MainActivityViewModel

class MainActivity : FragmentActivity() {

    lateinit var viewModel : MainActivityViewModel
    lateinit var fragmentManager : FragmentManager
    lateinit var fragmentTransaction : FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
    }

    fun initViews() {
        fragmentManager = getSupportFragmentManager()
        initViewModel()
        addFragment(UserMobileNoFragment())
    }

    fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        /*viewModel.enroll().observe(this, object : Observer<UserDetailRes> {
            override fun onChanged(userResponse: UserDetailRes?) {

            }
        })*/
    }

    fun addFragment(fragment : Fragment, clearStack : Boolean = false) {

        /*if (fragment is UserMobileNoFragment) {
            if (fragmentManager.backStackEntryCount > 1) {
                finish()
                return
            }
        }*/
        fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        fragmentTransaction.replace(R.id.container, fragment)
        fragmentTransaction.addToBackStack(fragment::class.java.simpleName)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        if (fragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    fun showDialog(title : String, msg : String, isExitApp : Boolean = false) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setCancelable(false)
        builder.setPositiveButton("OK", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                if (isExitApp) {
                    finish()
                }
            }
        }).show()
    }
}
