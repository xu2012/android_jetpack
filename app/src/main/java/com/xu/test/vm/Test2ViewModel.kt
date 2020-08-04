package com.xu.test.vm

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.xu.base.BaseViewModel
import com.xu.base.request
import com.xu.test.Config
import com.xu.test.model.Test2Model

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/23 15:43
 * @version 2.2
 */
class Test2ViewModel(application: Application):BaseViewModel<Test2Model>(application) {

    val configs =MutableLiveData<List<Config>>()
    val stateValue by lazy { MutableLiveData<Int>() }
    fun getConfig2(){
        /*request({
            model.getConfigs()
        },loadState)*/
        stateValue.value=0
        launch(
            block = {
                model.getConfigs()
//                stateValue.value=1
            },
            showLoading = false,
            error = {
                stateValue.value=-1
            },
            index = "config"
        )
    }
    fun getConfig(){
     /*   initLoad({
            model.getConfigs()
        },loadState)*/
        launch(
            block = {
                model.getConfigs()
            },
            showLoading = true,
            error = {
            },
            index = "config"
        )
    }
}