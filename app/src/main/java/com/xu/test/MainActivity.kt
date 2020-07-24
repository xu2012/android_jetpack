package com.xu.test

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.billy.android.loading.Gloading
import com.xu.base.BaseViewModelActivity
import com.xu.test.vm.TestViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseViewModelActivity<TestViewModel>() {
    private lateinit var mHolder: Gloading.Holder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mHolder = Gloading.getDefault().wrap(this).withRetry { onLoadRetry() }
        button.setOnClickListener {
            viewModel.getConfig()
        }
        button2.setOnClickListener {
            viewModel.getConfig2()
        }
    }

    private fun onLoadRetry() {
        viewModel.getConfig()
    }

    override fun showLoading() {
        mHolder.showLoading()
    }

    override fun showEmpty() {
        mHolder.showEmpty()
    }

    override fun showError() {
        mHolder.showLoadFailed()
    }

    override fun showSuccess() {
        mHolder.showLoadSuccess()
    }
    override fun initObserver() {
        viewModel.configs.observe(this, Observer {
            //这里处理业务异常？
        })
    }

    override fun layoutID(): Int = R.layout.activity_main
}