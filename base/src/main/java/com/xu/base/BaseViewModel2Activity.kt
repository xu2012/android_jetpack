package com.xu.base

import android.app.ProgressDialog
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xu.base.utils.CommonUtil

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/23 14:14
 * @version 2.2
 */
abstract class BaseViewModel2Activity<VM : BaseViewModel<*>> : BaseActivity() {
    protected lateinit var viewModel: VM
    private var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                CommonUtil.getClass(this)
            )
        initObserver()
        viewModel.loading.observe(this, Observer {
            if (it){
                showProgress()
            }else{
                hideProgress()
            }
        })
        viewModel.loginState.observe(this, Observer {
            if (!it){
                reLogin()
            }
        })
    }


    fun reLogin() {
    }

    open fun hideProgress() {
        progressDialog?.dismiss()
    }

    open fun showProgress() {
        if (null == progressDialog) {
            progressDialog = ProgressDialog(this)
        }
        progressDialog?.show()
    }

    abstract fun initObserver()
}