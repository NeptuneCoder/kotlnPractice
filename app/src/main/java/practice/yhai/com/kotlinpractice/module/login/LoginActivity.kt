package practice.yhai.com.kotlinpractice.module.login

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import manage.ad.yiba.com.admanage.common.extension.toColor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.textColor
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
        SignInBtn.textColor = R.color.colorAccent.toColor(this)
        initListener()
    }

    private val initListener = {
        SignInBtn.onClick { startActivity<MainActivity>() }
    }

}
