package practice.yhai.com.kotlinpractice.common.retrofit

import practice.yhai.com.kotlinpractice.module.main.Data
import retrofit2.Call
import retrofit2.http.*

/**
 * Created by yh on 9/23/17.
 */

interface ApiStore  {

    @GET("hourly?key=ca9c232c36ae4f8da8c66bd1e07a89a8")
    fun getWeather(@Query("city") cityCode: String): Call<Data>



}