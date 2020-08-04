package com.xu.test.model

import androidx.lifecycle.MutableLiveData
import com.xu.base.BaseModel
import com.xu.base.LoadState
import com.xu.base.dataConvert
import com.xu.base.initConvert
import com.xu.test.Config
import com.xu.test.SimaskuApi

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/23 15:47
 * @version 2.2
 */
class Test2Model:BaseModel() {
    suspend fun getConfigs(): List<Config>? {
        return SimaskuApi.create().getAppConfig().dataConvert()
    }
}