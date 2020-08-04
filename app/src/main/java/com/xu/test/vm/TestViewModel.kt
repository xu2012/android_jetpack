package com.xu.test.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.xu.base.BaseViewModel
import com.xu.base.initLoad
import com.xu.base.request
import com.xu.test.Config
import com.xu.test.model.Test2Model
import com.xu.test.model.TestModel

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/23 15:43
 * @version 2.2
 */
class TestViewModel(application: Application):BaseViewModel<TestModel>(application) {
    val configs =MutableLiveData<List<Config>>()
    fun getConfig2(){
        request({
            model.getConfigs()
        },loadState)

    }
    fun getConfig(){
        initLoad({
            model.getConfigs()
        },loadState)

    }
}