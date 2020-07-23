package com.xu.common.net

import android.content.Context
import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xu.common.net.interceptor.HttpLogInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.Proxy
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.CertificateFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManagerFactory

/**
 * @Description:
 * @Author:
 * @Date: 2019/12/2 16:39
 */
abstract class NetWorkApi protected constructor() {
    private var mOkHttpClient: OkHttpClient? = null
    protected fun getRetrofit(service: Class<*>): Retrofit? {
        val mBaseUrl = baseUrl()
        if (retrofitHashMap[mBaseUrl + service.name] != null) {
            return retrofitHashMap[mBaseUrl + service.name]
        }
        try {
            val retrofitBuilder = Retrofit.Builder()
            val retrofit = retrofitBuilder.baseUrl(mBaseUrl)
                            .client(okHttpClient)
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(CoroutineCallAdapterFactory())
                            .build()
            retrofitHashMap[mBaseUrl + service.name] = retrofit
            return retrofit
        } catch (e: Exception) {
            Log.e("xu",e.localizedMessage)
        }
        return null
    }

    private val okHttpClient: OkHttpClient?
        get() {
            if (mOkHttpClient == null) {
                val okHttpClientBuilder = OkHttpClient.Builder()
                setInterceptors()?.let {
                    for (interceptor in it) {
                        okHttpClientBuilder.addInterceptor(interceptor)
                    }
                }
                okHttpClientBuilder
                    .connectTimeout(mConfig.connectTimeOut(),TimeUnit.SECONDS)
                    .readTimeout(mConfig.readTimeOut(),TimeUnit.SECONDS)
                    .writeTimeout(mConfig.readTimeOut(),TimeUnit.SECONDS)
                if (mConfig.isDebug()) {
                    val httpLoggingInterceptor =
                        HttpLogInterceptor()
                    httpLoggingInterceptor.level = HttpLogInterceptor.Level.BODY
                    okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)
                }else{
                    okHttpClientBuilder.proxy(Proxy.NO_PROXY)
                }
                mOkHttpClient = okHttpClientBuilder.build()
            }
            return mOkHttpClient
        }


    companion object {
        private lateinit var mConfig: IConfig
        private val retrofitHashMap = HashMap<String, Retrofit>()

        fun init(config: IConfig) {
            mConfig = config
        }

        private fun getSSLSocketFactory(
            context: Context?,
            certificates: IntArray
        ): SSLSocketFactory? {
            if (context == null) {
                throw NullPointerException("context == null")
            }
            //CertificateFactory用来证书生成
            val certificateFactory: CertificateFactory
            try {
                certificateFactory = CertificateFactory.getInstance("X.509")
                //Create a KeyStore containing our trusted CAs
                val keyStore =
                    KeyStore.getInstance(KeyStore.getDefaultType())
                keyStore.load(null, null)
                for (i in certificates.indices) { //读取本地证书
                    val `is` =
                        context.resources.openRawResource(certificates[i])
                    keyStore.setCertificateEntry(
                        i.toString(), certificateFactory
                            .generateCertificate(`is`)
                    )
                    `is`.close()
                }
                //Create a TrustManager that trusts the CAs in our keyStore
                val trustManagerFactory =
                    TrustManagerFactory
                        .getInstance(TrustManagerFactory.getDefaultAlgorithm())
                trustManagerFactory.init(keyStore)
                //Create an SSLContext that uses our TrustManager
                val sslContext =
                    SSLContext.getInstance("TLS")
                sslContext.init(
                    null,
                    trustManagerFactory.trustManagers,
                    SecureRandom()
                )
                return sslContext.socketFactory
            } catch (e: Exception) {
            }
            return null
        }
    }

    /**
     * 设置api的baseUrl
     * @return String
     */
    protected abstract fun baseUrl():String

    /**
     * 添加自定义拦截器
     * @return List<Interceptor>?
     */
    protected abstract fun setInterceptors(): List<Interceptor>?
}