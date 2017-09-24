package practice.yhai.com.kotlinpractice.common.retrofit

import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by yh on 9/23/17.
 */
object YHaiServer {
    val BASE_URL = "https://free-api.heweather.com/v5/"
    private val DEFAULT_TIMEOUT = 45

    private var retrofit: Retrofit
    var apiStore: ApiStore


    init {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.SECONDS)
        clientBuilder.writeTimeout(10000, TimeUnit.SECONDS)
        clientBuilder.readTimeout(10000, TimeUnit.SECONDS)

        retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        apiStore = retrofit.create(ApiStore::class.java)


    }

}