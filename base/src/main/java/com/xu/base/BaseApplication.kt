package com.xu.base

import android.app.Application

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/8/4 11:09
 * @version 2.2
 */
open class BaseApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object{
        lateinit var instance: BaseApplication
    }
}