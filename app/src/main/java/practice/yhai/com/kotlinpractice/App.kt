package practice.yhai.com.kotlinpractice

import android.app.Application
import kotlin.properties.Delegates

/**
 * Created by yh on 9/23/17.
 */
 class App : Application() {
    companion object {
        var instance: App by Delegates.notNull()

    }
    override fun onCreate() {
        super.onCreate()

    }
}