package com.xu.base

import java.lang.Exception

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/7/24 10:53
 * @version 2.2
 */
class ApiException(val code:String?, override val message:String?):Exception(message)