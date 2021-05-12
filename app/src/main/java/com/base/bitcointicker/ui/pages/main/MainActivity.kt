package com.base.bitcointicker.ui.pages.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.base.core.helpers.LocalPrefManager
import com.base.bitcointicker.R
import com.base.bitcointicker.ui.base.activity.BaseActivity
import com.base.bitcointicker.ui.pages.detail.DetailActivity
import com.base.bitcointicker.ui.pages.home.HomeFragment
import com.base.bitcointicker.ui.pages.main.viewmodel.MainActivityViewModel
import com.base.bitcointicker.ui.pages.favorites.FavoritesFragment
import com.base.component.constant.PrefConstants
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override val viewModelClass = MainActivityViewModel::class.java
    override val layoutViewRes = R.layout.activity_main

    @Inject
    lateinit var localPrefManager: LocalPrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setupBottomBarView()
    }

    private fun setupBottomBarView() {
        var fragment: Fragment? = HomeFragment()
        fragment?.let {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, it)
                .commit()
        }

        bottomNavigationView.itemIconTintList = null
        val bottomNavBarClickListener =
            BottomNavigationView.OnNavigationItemSelectedListener { p0 ->
                when (p0.itemId) {
                    R.id.navigation_home -> {
                        fragment = HomeFragment()
                    }
                    R.id.navigation_favorites -> {
                        fragment = FavoritesFragment()
                    }
                    R.id.navigation_logout -> {
                        localPrefManager.push(PrefConstants.PREF_USER_IS_LOGIN, false)
                        val intent = Intent(this, DetailActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }

                if (fragment != null) {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.main_container, fragment!!)
                        .commit()
                }

                true
            }

        bottomNavigationView.setOnNavigationItemSelectedListener(bottomNavBarClickListener)
    }
}
