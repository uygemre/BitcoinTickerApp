package com.base.bitcointicker.ui.pages.detail

import android.content.Intent
import android.os.Bundle
import com.base.bitcointicker.R
import com.base.bitcointicker.ui.base.activity.BaseActivity
import com.base.bitcointicker.ui.base.fragment.BaseFragment
import com.base.bitcointicker.ui.common.enums.IntentBundleKeyEnum
import com.base.bitcointicker.ui.pages.coindetail.CoinDetailFragment
import com.base.bitcointicker.ui.pages.detail.viewmodel.DetailActivityViewModel
import com.base.bitcointicker.ui.pages.login.LoginFragment
import com.base.bitcointicker.ui.pages.register.RegisterFragment
import com.base.core.helpers.LocalPrefManager
import javax.inject.Inject

class DetailActivity : BaseActivity<DetailActivityViewModel>() {

    override val viewModelClass = DetailActivityViewModel::class.java
    override val layoutViewRes = R.layout.activity_detail

    @Inject
    lateinit var localPrefManager: LocalPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openRelatedFragment(intent)
    }

    private fun openRelatedFragment(intent: Intent?) {
        intent?.let {
            when (it.getStringExtra(IntentBundleKeyEnum.DETAIL_KEY.toString())) {
                IntentBundleKeyEnum.DETAIL_COIN.toString() -> {
                    navigateToFragment(CoinDetailFragment.newInstance(it.extras))
                }
                IntentBundleKeyEnum.DETAIL_REGISTER.toString() -> {
                    navigateToFragment(RegisterFragment())
                }
                IntentBundleKeyEnum.DETAIL_LOGIN.toString() -> {
                    navigateToFragment(LoginFragment())
                }
                else -> {
                    navigateToFragment(LoginFragment())
                }
            }
        }
    }

    private fun navigateToFragment(fragment: BaseFragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.detail_container, fragment)
        transaction.commit()
    }
}