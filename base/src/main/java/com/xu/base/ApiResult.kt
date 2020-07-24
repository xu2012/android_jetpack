package com.xu.base

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/5/14 11:20
 * @version 2.2
 */
 class ApiResult<T> {
    var code: String? = null
    var error: ErrorBean? = null
    var content: T? = null
     class ErrorBean {
        var details: String? = null
        var message: String? = null
        var errors: List<ErrorsBean>? = null
        class ErrorsBean {
            var message: String? = null
            var parameter: String? = null
        }
    }
}