package com.xu.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.xu.base.utils.CommonUtil

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/23 14:26
 * @version 2.2
 */
abstract class BaseViewModel<T : BaseModel>(application: Application) :
    AndroidViewModel(application) {
    val loadState by lazy { MutableLiveData<LoadState>() }
    val model: T by lazy {
        (CommonUtil.getClass<T>(this))
            // 获取构造函数的构造器，传入参数class
            .getDeclaredConstructor(MutableLiveData::class.java)
            .newInstance(loadState)
    }
}