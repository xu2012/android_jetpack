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
abstract class BaseViewModelActivity<VM : BaseViewModel<*>> : BaseActivity() {
    protected lateinit var viewModel: VM
    private var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
                CommonUtil.getClass(this)
            )
        initObserver()
        viewModel.loadState.observe(this, Observer {
            when (it.status) {
                StateType.LOADING -> {
                    showProgress(it.tag)
                }
                StateType.SUCCESS -> {
                    hideProgress(it.tag)
                }
                StateType.ERROR -> {
                    hideProgress(it.tag)
                    if (!TextUtils.isEmpty(it.code) && it.code.startsWith("40000")) {
//                        SPUtils.removeBykey("user")
                        reLogin()
                    } else {
//                        showToast(it.message)
                        onError(it.code, it.message, it.tag)
                    }
                }
                StateType.TIP -> {
                    showToast(it.tag,it.message)
                }
                else -> {}
            }
        })
    }

     fun showToast(tag: String,message: String?) {
         message?.let {
             Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
         }
    }

     fun onError(code: String, message: String?, tag: String) {
         message?.let {
             Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
         }
    }

     fun reLogin() {
    }

     open fun hideProgress(tag: String) {
        progressDialog?.dismiss()
    }

    open fun showProgress(tag: String) {
        if (null == progressDialog) {
            progressDialog = ProgressDialog(this)
        }
        progressDialog?.show()
    }


    abstract fun initObserver()
}