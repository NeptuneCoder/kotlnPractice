package practice.yhai.com.kotlinpractice.module.launch

import android.os.Bundle
import android.os.Handler
import org.jetbrains.anko.startActivity
import practice.yhai.com.kotlinpractice.common.BaseActivity
import practice.yhai.com.kotlinpractice.R
import practice.yhai.com.kotlinpractice.module.login.LoginActivity

class LaunchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        Handler().postDelayed({
            startActivity<LoginActivity>()
        },2000)
    }
}
