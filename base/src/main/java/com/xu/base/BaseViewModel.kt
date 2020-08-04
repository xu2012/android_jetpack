package com.xu.base

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xu.base.utils.CommonUtil
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

typealias Block<T> = suspend () -> T
typealias Error = suspend (e: Exception) -> Unit
typealias Cancel = suspend (e: Exception) -> Unit
typealias Failed = suspend (e: ApiException) -> Unit

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/23 14:26
 * @version 2.2
 */
abstract class BaseViewModel<T : BaseModel>(application: Application) :
    AndroidViewModel(application) {
    val loadState by lazy { MutableLiveData<LoadState>() }
    val loading by lazy {
        MutableLiveData<Boolean>()
    }
    val loginState by lazy {
        MutableLiveData<Boolean>()
    }
    val model: T by lazy {
        /*(CommonUtil.getClass<T>(this))
            // 获取构造函数的构造器，传入参数class
            .getDeclaredConstructor(MutableLiveData::class.java)
            .newInstance(loadState)*/
        (CommonUtil.getClass<T>(this))
            // 获取构造函数的构造器，传入参数class
            .getDeclaredConstructor()
            .newInstance()
    }

    protected fun launch(
        block: Block<Unit>,
        error: Error? = null,
        cancel: Cancel? = null,
        showLoading: Boolean = true,
        index: String = ""
    ): Job {
        return viewModelScope.launch {
            try {
                if (showLoading) {
                    loading.value = true
                }
                block.invoke()
            } catch (e: Exception) {
                when (e) {
                    is CancellationException -> {
                        cancel?.invoke(e)
                    }
                    is ApiException -> {
                        if (e.code?.startsWith("40000")!!) {
                            //登录失效
                            loginState.value = false
                        } else {
                            onFailed(index, e.code, e.message)
                        }
                    }
                    else -> {
                        onError(e, index)
                        error?.invoke(e)
                    }
                }
            } finally {
                if (showLoading) {
                    loading.value = false
                }
            }
        }
    }


    open fun onError(e: Exception, index: String) {
        ExceptionHandler.handException(e)

    }


    /**
     * 业务异常处理
     * @param index String 请求标识
     * @param code String 错误码
     * @param message String? 错误信息
     */
    open fun onFailed(index: String, code: String, message: String?) {
        //业务异常
        message?.let {
            Toast.makeText(BaseApplication.instance, it, Toast.LENGTH_SHORT).show()
        }

    }
}