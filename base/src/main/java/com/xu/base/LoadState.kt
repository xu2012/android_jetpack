package com.xu.base

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/23 14:45
 * @version 2.2
 */
data class LoadState(var status: StateType, var message:String?="",val code:String="-1",val tag:String="")
