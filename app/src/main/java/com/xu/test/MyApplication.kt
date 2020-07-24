package com.xu.test

import android.app.Application
import com.billy.android.loading.Gloading
import com.xu.common.net.IConfig
import com.xu.common.net.NetWorkApi
import com.xu.test.loading.GlobalAdapter


/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/24 11:38
 * @version 2.2
 */
class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        NetWorkApi.init(object: IConfig {
            override fun isDebug(): Boolean {
                return true
            }

            override fun connectTimeOut(): Long {
                return 10000
            }

            override fun readTimeOut(): Long {
                return 10000
            }
        })
        Gloading.initDefault(GlobalAdapter())
    }
}