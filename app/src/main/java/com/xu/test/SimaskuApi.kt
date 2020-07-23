package com.xu.test

import com.xu.common.net.NetWorkApi
import okhttp3.Interceptor

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/23 13:48
 * @version 2.2
 */
object SimaskuApi:NetWorkApi() {

    override fun baseUrl(): String {
        return "http://www.baidu.com"
    }

    override fun setInterceptors(): List<Interceptor>? {
        return null
    }
    fun create():ApiService{
        return ApiService::class.java.let {
            getRetrofit(it)?.create(it)!!
        }
    }
}