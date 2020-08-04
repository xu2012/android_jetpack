package com.xu.base

import android.widget.Toast
import org.json.JSONException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/8/4 15:09
 * @version 2.2
 */
object ExceptionHandler {
    fun handException(e:Exception){
        when (e) {
            is SocketTimeoutException, is TimeoutException -> {
                Toast.makeText(BaseApplication.instance, "请求超时", Toast.LENGTH_SHORT).show()
            }
            is ConnectException, is UnknownHostException -> {
                Toast.makeText(BaseApplication.instance, "连接服务器失败", Toast.LENGTH_SHORT).show()
            }
            is JSONException -> {
                Toast.makeText(BaseApplication.instance, "数据异常", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(BaseApplication.instance, "未知错误", Toast.LENGTH_SHORT).show()
            }
        }
    }
}