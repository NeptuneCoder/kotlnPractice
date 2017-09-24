package practice.yhai.com.kotlinpractice.module.login

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import practice.yhai.com.kotlinpractice.R
import practice.yhai.com.kotlinpractice.common.BaseActivity
import practice.yhai.com.kotlinpractice.module.main.MainActivity

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : BaseActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initListener()
    }

    private val initListener = {
        SignInBtn.onClick { startActivity<MainActivity>() }
    }

}
