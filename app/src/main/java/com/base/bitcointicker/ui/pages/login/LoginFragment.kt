package com.base.bitcointicker.ui.pages.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.base.bitcointicker.R
import com.base.bitcointicker.ui.base.fragment.BaseViewModelFragment
import com.base.bitcointicker.ui.common.enums.IntentBundleKeyEnum
import com.base.bitcointicker.ui.pages.detail.DetailActivity
import com.base.bitcointicker.ui.pages.login.viewmodel.LoginFragmentViewModel
import com.base.bitcointicker.ui.pages.main.MainActivity
import com.base.component.constant.PrefConstants
import com.base.core.helpers.LocalPrefManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_login.*
import java.util.*
import javax.inject.Inject

class LoginFragment() : BaseViewModelFragment<LoginFragmentViewModel>() {

    override val viewModelClass = LoginFragmentViewModel::class.java
    override val layoutViewRes = R.layout.fragment_login

    @Inject
    lateinit var localPrefManager: LocalPrefManager
    lateinit var authorization: FirebaseAuth
    private val bundle = Bundle()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authorization = FirebaseAuth.getInstance()

        btn_login.setOnClickListener {
            val email = edt_email.text.toString().toLowerCase(Locale.getDefault())
            val password = edt_password.text.toString().toLowerCase(Locale.getDefault())

            authorization.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity()) {
                    if (it.isSuccessful) {
                        localPrefManager.push(PrefConstants.PREF_USER_IS_LOGIN, true)
                        val intent = Intent(activity, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(requireContext(), "Wrong password or email!", Toast.LENGTH_LONG).show()
                    }
                }
        }

        btn_register.setOnClickListener {
            val intent = Intent(activity, DetailActivity::class.java)
            bundle.putString(
                IntentBundleKeyEnum.DETAIL_KEY.toString(),
                IntentBundleKeyEnum.DETAIL_REGISTER.toString()
            )
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
}