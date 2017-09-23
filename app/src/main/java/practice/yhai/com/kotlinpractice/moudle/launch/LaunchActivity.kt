package practice.yhai.com.kotlinpractice.moudle.launch

import android.os.Bundle
import android.os.Handler
import practice.yhai.com.kotlinpractice.common.BaseActivity
import practice.yhai.com.kotlinpractice.R

class LaunchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        Handler().postDelayed(object: Runnable{
            override fun run() {
            }

        },2000)


        Handler().postDelayed({

        },2000)
    }
}
