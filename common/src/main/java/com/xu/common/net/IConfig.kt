package com.xu.common.net

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/23 11:36
 * @version 2.2
 */
interface IConfig {
    fun isDebug(): Boolean
    fun connectTimeOut(): Long
    fun readTimeOut(): Long
}