package com.xu.base

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xu.base.utils.CommonUtil

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/8/3 11:22
 * @version 2.2
 */
abstract class BaseVmFragment<VM:BaseViewModel<*>>:BaseFragment() {
    protected lateinit var viewModel: VM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(activity?.application!!)).get(
                CommonUtil.getClass(this)
            )
        initObserver()
        viewModel.apply {
            loadState.observe(this@BaseVmFragment, Observer {

            })
            loading.observe(this@BaseVmFragment, Observer {

            })
        }
        initView()
        initData()
    }

    abstract fun initObserver()

    abstract fun initView()

    abstract fun initData()
}