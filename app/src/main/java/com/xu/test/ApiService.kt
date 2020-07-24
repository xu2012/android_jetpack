package com.xu.test

import com.google.gson.JsonObject
import com.xu.base.ApiResult
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Description:
 * @author: xuyunlong
 * Date: 2020/5/25 17:48
 * @version 2.2
 */
interface ApiService {
    /**
     * app配置
     * @return ApiResult<List<AppConfig>>
     */
    @GET("/api/simasku/pub/1.0.0/app/listConfigs")
    suspend fun getAppConfig(): ApiResult<List<Config>>

    /**
     * 轮播图
     * @return ApiResult<List<BannerBean>>
     */
    @GET("/api/simasku/pub/1.0.0/api/banner/list")
    suspend fun getBanners(): ApiResult<List<BannerBean>>


    /**
     * 登录
     * @param params LoginParams
     * @return ApiResult<AmountVo>
     */
    @POST("/api/simasku/pvt/1.0.0/user/login")
    suspend fun login(@Body params: JsonObject): ApiResult<LoginVo>

    @POST("/api/simasku/pub/1.0.0/api/sms/send")
    suspend fun sendCode(@Body vo: JsonObject?): ApiResult<String>
}