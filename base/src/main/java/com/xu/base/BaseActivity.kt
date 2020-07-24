package com.xu.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/23 14:13
 * @version 2.2
 */
abstract class BaseActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutID())
        lifecycle.addObserver(AppManager)
    }

    abstract fun layoutID(): Int
    override fun onDestroy() {
        super.onDestroy()
    }
//    abstract fun initData(savedInstanceState: Bundle?)
}