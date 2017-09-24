package practice.yhai.com.kotlinpractice.module.main

/**
 * Created by yh on 9/24/17.
 */

data class Data(var HeWeather5: List<HeWeather5Bean>?)

data class HeWeather5Bean( var basic: BasicBean,var status: String,var hourly_forecast: List<HourlyForecastBean>)

data class BasicBean(  var city: String, var cnty: String,var id: String,var lat: String,var lon: String, var update: UpdateBean)

data class UpdateBean(var loc: String, var utc: String)

data class HourlyForecastBean(  var cond: CondBean,
                                var date: String,
                                var hum: String,
                                var pop: String,
                                var pres: String,
                                var tmp: String,
                                var wind: WindBean)
data class CondBean(var code: String, var txt: String)

data class WindBean(var deg: String,
                      var dir: String,
                      var sc: String,
                      var spd: String)

