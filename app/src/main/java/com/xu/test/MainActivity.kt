package com.xu.test

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.billy.android.loading.Gloading
import com.xu.base.BaseViewModel2Activity
import com.xu.material.MatrialActivity
import com.xu.navigation.NavigationActivity
import com.xu.test.dialog.CustomActivity
import com.xu.test.vm.Test2ViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseViewModel2Activity<Test2ViewModel>() {

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
        button3.setOnClickListener {
            startActivity(Intent(this@MainActivity,NavigationActivity::class.java))
        }
        button4.setOnClickListener {
            startActivity(Intent(this@MainActivity,
                MatrialActivity::class.java))
        }
    }

    private fun onLoadRetry() {
        viewModel.getConfig2()
    }

     fun showLoading() {
        mHolder.showLoading()
    }

     fun showEmpty() {
        mHolder.showEmpty()
    }

     fun showError() {
        mHolder.showLoadFailed()
    }

     fun showSuccess() {
        mHolder.showLoadSuccess()
    }
    override fun initObserver() {
        viewModel.configs.observe(this, Observer {
            //这里处理业务异常？
        })
        viewModel.apply {
            configs.observe(this@MainActivity, Observer {
                if (it.isEmpty()){
                    showEmpty()
                }else{
                    showSuccess()
                }
            })
            stateValue.observe(this@MainActivity, Observer {
                when (it) {
                    0 -> {
                        showLoading()
                    }
                    1->{
                        showSuccess()
                    }
                    -1->{
                        showError()
                    }
                    else -> {
                    }
                }
            })
        }
    }

    override fun layoutID(): Int = R.layout.activity_main
}