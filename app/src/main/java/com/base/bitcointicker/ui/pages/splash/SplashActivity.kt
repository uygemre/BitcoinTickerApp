package com.base.bitcointicker.ui.pages.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.base.bitcointicker.R
import com.base.bitcointicker.ui.base.activity.BaseActivity
import com.base.bitcointicker.ui.pages.detail.DetailActivity
import com.base.bitcointicker.ui.pages.main.MainActivity
import com.base.bitcointicker.ui.pages.splash.viewmodel.SplashActivityViewModel
import com.base.component.constant.PrefConstants
import com.base.core.helpers.LocalPrefManager
import javax.inject.Inject

class SplashActivity : BaseActivity<SplashActivityViewModel>() {

    override val layoutViewRes = R.layout.activity_splash
    override val viewModelClass = SplashActivityViewModel::class.java

    @Inject
    lateinit var localPrefManager: LocalPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        Handler().postDelayed({
            if (localPrefManager.pull(PrefConstants.PREF_USER_IS_LOGIN, false)) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, DetailActivity::class.java))
                finish()
            }

        }, 4000)
    }
}