package manage.ad.yiba.com.admanage.common.utils

import android.content.Context
import practice.yhai.com.kotlinpractice.App
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Created by yh on 8/25/17.
 */
class Preference<T>(context: Context = App.instance, val name: String?, val default: T) : ReadWriteProperty<Any?, T> {

    val prefs by lazy {
       context.getSharedPreferences("yiba",Context.MODE_PRIVATE)
    }
    override fun getValue(thisRef: Any?, property: KProperty<*>): T = with(prefs){
        when (default) {
            is Long -> {
                getLong(name, default)
            }
            is String -> {
                getString(name, default)
            }
            is Float -> {
                getFloat(name, default)
            }
            is Int -> {
                getInt(name, default)
            }
            is Boolean -> {
                getBoolean(name, default)
            }
            else -> {
                throw IllegalArgumentException("This type can be saved into Preferences")
            }
        } as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) = with(prefs.edit()){
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Float -> putFloat(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            else -> {
                throw  IllegalArgumentException("存储的类型不存在，请确认储存类型")
            }
        }.apply()
    }

}